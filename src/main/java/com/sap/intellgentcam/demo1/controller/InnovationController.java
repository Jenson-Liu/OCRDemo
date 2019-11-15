package com.sap.intellgentcam.demo1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/15  4:03 下午
 */
@Controller
@RequestMapping("/innovation")
public class InnovationController {

    private static final Logger logger = LoggerFactory.getLogger(InnovationController.class);

    @RequestMapping(value = "/camera",method = RequestMethod.GET)
    public String GetStart(){
        return "Camera/camera";
    }

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

//    @RequestMapping(value = "/index",method = RequestMethod.GET)
//    public String video(){
//        return "Camera/loading.html";
//    }
}

