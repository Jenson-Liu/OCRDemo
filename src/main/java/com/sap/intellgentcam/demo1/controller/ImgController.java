package com.sap.intellgentcam.demo1.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sap.intellgentcam.demo1.service.PurchaseService;
import com.sap.intellgentcam.demo1.tool.ApiTool;
import com.sap.intellgentcam.demo1.tool.ImgTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/importImg",method = RequestMethod.POST)
    public String ImportImg(@RequestParam("file") String  file, HttpServletRequest request) {
        request.getSession().setAttribute("imgBase64", file);
        return "Camera/edit";
    }

    @ResponseBody
    @RequestMapping(value = "/postImg",method = RequestMethod.POST)
    public String PostImg(@RequestParam("href")String href,HttpServletRequest request) throws IOException {
        String access_token = "";
        if(request.getSession().getAttribute("access_token") == null){
            try {
                access_token = ApiTool.getSessionToken();
                request.getSession().setAttribute("access_token",access_token);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            access_token = request.getSession().getAttribute("access_token").toString();
        }
        //set purchase session
        String img = href.substring(href.indexOf("base64,")+7);
        ImgTool.Base64ToImage(img,"./src/main/resources/static/images/example.png");
        String order = ApiTool.testToken("./src/main/resources/static/images/example.png",access_token);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(order,JsonObject.class);
        logger.info("jsonObject order:" + jsonObject.get("predictions"));

        return order;
    }

    @ResponseBody
    @RequestMapping(value = "/getImg",method = RequestMethod.GET)
    public String GetImg(HttpServletRequest request){
        return request.getSession().getAttribute("imgBase64").toString();
    }


}
