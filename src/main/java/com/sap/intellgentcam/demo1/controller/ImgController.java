package com.sap.intellgentcam.demo1.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sap.intellgentcam.demo1.service.PurchaseService;
import com.sap.intellgentcam.demo1.service.RedisService;
import com.sap.intellgentcam.demo1.tool.ApiTool;
import com.sap.intellgentcam.demo1.tool.ImgTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/24  11:13 上午
 */
@Controller
@RequestMapping("/innovation")
public class ImgController {

    private static final Logger logger = LoggerFactory.getLogger(ImgController.class);

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    RedisService redisService;

    /**
     *
     * @param href
     * @param request
     * @param response
     * @throws FileNotFoundException
     */
    @ResponseBody
    @RequestMapping(value = "/importImg",method = RequestMethod.POST)
    public void ImportImg(@RequestParam("href") String  href, HttpServletRequest request,HttpServletResponse response) {
        String img = href.substring(href.indexOf("base64,")+7);
        redisService.setImgBase64(href);
    }

    /**
     * post Img to get poNum
     * @param editImg
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/postImg",method = RequestMethod.POST)
    public String  PostImg(@RequestParam("editImg")String editImg) throws IOException {
        String access_token = null;
        if(redisService.getMLToken() == null){
            try {
                access_token = ApiTool.getSessionToken();
                redisService.setMLToken(access_token);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            access_token = redisService.getMLToken() ;
        }
        redisService.setString("editImg",editImg);
        String img = editImg.substring(editImg.indexOf("base64,")+7);
        File ImgFile = ImgTool.Base64ToImage(img,"afterEdit.png");
        String order = ApiTool.TransferImg(ImgFile,access_token);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(order,JsonObject.class);
        String poNum = String.valueOf(jsonObject.get("predictions"));
        logger.info("before poNum:"+poNum);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < poNum.length(); i++) {
            char ch = poNum.charAt(i);
            if(48<=ch&&ch<=57){
                stringBuffer.append(ch);
            }
        }
        poNum = stringBuffer.toString();
        logger.info("after poNum:" + poNum);
        redisService.setString("poNum",poNum);
        return poNum;
    }

    /**
     *
     * @return
     * @throws FileNotFoundException
     */
    @ResponseBody
    @RequestMapping(value = "/getImg",method = RequestMethod.GET)
    public String GetImg(HttpServletRequest request) {
        String imgBase64 = redisService.getImageBase64();
        return imgBase64;
    }



}
