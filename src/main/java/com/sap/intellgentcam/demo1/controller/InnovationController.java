package com.sap.intellgentcam.demo1.controller;

import com.sap.intellgentcam.demo1.service.PurchaseServiceApi;
import com.sap.intellgentcam.demo1.tool.XmlTool;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/15  4:03 下午
 */
@Controller
@RequestMapping("/innovation")
public class InnovationController {

    private static final Logger logger = LoggerFactory.getLogger(InnovationController.class);

    @Autowired
    PurchaseServiceApi purchaseServiceApi;

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String GetStart2(){
        return "Camera/showcamera";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String getEdit(){
        return "Camera/edit";
    }

    @RequestMapping(value = "/display",method = RequestMethod.GET)
    public String getDisplay(@RequestParam("poNum")String poNum){
        return "Camera/display";
    }

    @RequestMapping(value = "/cropper",method = RequestMethod.GET)
    public String GetCropper(){
        return "Camera/cropper.html";
    }

    @ResponseBody
    @RequestMapping(value = "/getPoNum",method = RequestMethod.GET)
    public String video(@RequestParam("poNum")String poNum) throws IOException {
        logger.info("poNum:"+poNum);
        purchaseServiceApi.getPurchase(poNum);
        return "hello";
    }
}

