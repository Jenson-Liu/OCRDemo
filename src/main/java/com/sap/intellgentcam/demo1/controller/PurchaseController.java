package com.sap.intellgentcam.demo1.controller;

import com.sap.intellgentcam.demo1.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/22  3:56 下午
 */
@Controller
@RequestMapping("/innovation")
public class PurchaseController {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseController.class );

    @Autowired
    PurchaseService purchaseService;

    @ResponseBody
    @RequestMapping(value = "/purchase/{poNum}",method = RequestMethod.GET)
    public String getPurchase(@PathVariable("poNum")String poNum){
        String purchase = purchaseService.getPurchase(poNum);
        return purchase;
    }




}
