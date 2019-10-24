package com.sap.intellgentcam.demo1.controller;

import com.sap.intellgentcam.demo1.tool.ApiTool;
import com.sap.intellgentcam.demo1.tool.ImgTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/15  4:03 下午
 */
@Controller
@RequestMapping("/innovation")
public class InnovationController {

    private static final Logger logger = LoggerFactory.getLogger(InnovationController.class);


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String GetStart(){
        return "Camera/index";
    }



    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String GetEdit(){
        return "Camera/edit";
    }

    @RequestMapping(value = "/display",method = RequestMethod.GET)
    public String GetDisplay(){
        return "Camera/display";
    }

}

