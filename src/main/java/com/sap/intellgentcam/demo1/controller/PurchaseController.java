package com.sap.intellgentcam.demo1.controller;

import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.purchaseorder.PurchaseOrder;
import com.sap.intellgentcam.demo1.result.AjaxVO;
import com.sap.intellgentcam.demo1.service.PostGoodsReceiptService;
import com.sap.intellgentcam.demo1.service.PurchaseService;
import com.sap.intellgentcam.demo1.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

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

    @Autowired
    PostGoodsReceiptService postGoodsReceiptService;

    @Autowired
    RedisService redisService;

    /**
     *
     * @param poNum
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPo",method = RequestMethod.GET)
    public AjaxVO getPo(@RequestParam("poNum")String poNum){
        PurchaseOrder purchaseOrder = null;
        HashMap<String,String> hashMap = new HashMap<String, String>(5);
        try {
            purchaseOrder = purchaseService.getPurchase(poNum);
        } catch (ODataException e) {
            AjaxVO ajaxVO = new AjaxVO(false,"Purchase Order Doesn't Exist in S/4HANA Cloud","");
            return ajaxVO;
        }
        if(purchaseOrder == null){
            return new AjaxVO(false,"Enter Your Purchase Order Number","");
        }else {
                String purchaseOrderNum = purchaseOrder.getPurchaseOrder();
                String CompanyCode = purchaseOrder.getCompanyCode();
                String PurchaseOrderType = purchaseOrder.getPurchaseOrderType();
                String Supplier = purchaseOrder.getSupplier();
                hashMap.put("PurchaseOrder",purchaseOrderNum);
                hashMap.put("CompanyCode",CompanyCode);
                hashMap.put("PurchaseOrderType",PurchaseOrderType);
                hashMap.put("Supplier",Supplier);
            return new AjaxVO(true,"",hashMap);
        }
    }

    /**
     *
     * @return
     * @throws FileNotFoundException
     */
    @ResponseBody
    @RequestMapping(value = "/getDetail",method = RequestMethod.GET)
    public AjaxVO getDetail() throws FileNotFoundException {
        String poNum = null;
        String editImg = null;
        try {
            poNum = redisService.getString("poNum");
        }catch (Exception e){
            return new AjaxVO(false,"can't identify the image","");
        }
        logger.info("poNum:"+poNum);
        HashMap<String,String> hashMap = new HashMap<String, String>(5);
        PurchaseOrder purchaseOrder = null;
        try {
            if(poNum.equals("")){
                hashMap.put("PurchaseOrder",poNum);
                hashMap.put("editImg",redisService.getString("editImg"));
                return new AjaxVO(false,"Recognition of Purchase Order No. Failed",hashMap);
            }else {
                purchaseOrder = purchaseService.getPurchase(poNum);
            }
        } catch (Exception e) {
            hashMap.put("PurchaseOrder",poNum);
            hashMap.put("editImg",redisService.getString("editImg"));
            return new AjaxVO(false,"Purchase Order Doesn't Exist in S/4HANA Cloud System",hashMap);
        }
        if(purchaseOrder == null){
            hashMap.put("PurchaseOrder",poNum);
            hashMap.put("CompanyCode","");
            hashMap.put("PurchaseOrderType","");
            hashMap.put("Supplier","");
        }else {
            String purchaseOrderNum = purchaseOrder.getPurchaseOrder();
            String CompanyCode = purchaseOrder.getCompanyCode();
            String PurchaseOrderType = purchaseOrder.getPurchaseOrderType();
            String Supplier = purchaseOrder.getSupplier();
            hashMap.put("PurchaseOrder",purchaseOrderNum);
            hashMap.put("CompanyCode",CompanyCode);
            hashMap.put("PurchaseOrderType",PurchaseOrderType);
            hashMap.put("Supplier",Supplier);
        }
        hashMap.put("editImg", redisService.getString("editImg"));
        return new AjaxVO(true,"success",hashMap);
    }

    /**
     * PGR
     * @param id
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/postGoodsReceipt/{num}",method = RequestMethod.POST)
    public AjaxVO  getTest(@PathVariable("num")String id) throws IOException {
        String csrfToken = null;
        String etag = null;
        String result = null;
        if(csrfToken == null) {
            try {
                csrfToken = postGoodsReceiptService.getCsrfToken();
            } catch (Exception e) {
                return new AjaxVO(false, "can't get csrfToken", "");
            }
        }
        try {
            HashMap<String, Object> putAway = postGoodsReceiptService.putAway(id,csrfToken);
            if((Boolean)putAway.get("result") == false){
                return new AjaxVO(false,putAway.get("info").toString(),"");
            }
        }catch (IOException e){
            return new AjaxVO(false,"please check the Inbound Delivery","");
        }
        try {
            etag = postGoodsReceiptService.getEtagMatch(id,csrfToken);
        }catch (IOException e){
            return new AjaxVO(false,"Please Check the Inbound Delivery","");
        }
        result = postGoodsReceiptService.postGoodsReceipt(id,csrfToken,etag);
        return new AjaxVO(true,result,"");
    }

    /**
     * PGR without Po
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/postGoodsReceipt/",method = RequestMethod.POST)
    public AjaxVO  getTest() throws IOException {
        return new AjaxVO(false,"inbound delivery is none","");
    }

}
