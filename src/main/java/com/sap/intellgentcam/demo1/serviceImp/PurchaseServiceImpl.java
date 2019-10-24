package com.sap.intellgentcam.demo1.serviceImp;

import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.s4hana.connectivity.ErpConfigContext;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultPurchaseOrderService;
import com.sap.intellgentcam.demo1.service.PurchaseService;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.purchaseorder.PurchaseOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/23  9:45 上午
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private static final Logger logger = LoggerFactory.getLogger( PurchaseServiceImpl.class );

    @Override
    public String getPurchase(String poNum) {
        logger.info("ALLOW_MOCKED_AUTH_HEADER: " + System.getenv("ALLOW_MOCKED_AUTH_HEADER"));
        logger.info("destinations: " + System.getenv("destinations"));

        String destinations = "ErpQueryEndpoint";
        ErpConfigContext erpConfigContext = new ErpConfigContext("ErpQueryEndpoint");
        List<PurchaseOrder> purchaseOrder = null;
        logger.info("erpConfigContext info:"+erpConfigContext.toString());
        try {
            purchaseOrder =
                    new DefaultPurchaseOrderService().getAllPurchaseOrder()
                            .select( PurchaseOrder.PURCHASE_ORDER,
                                    PurchaseOrder.COMPANY_CODE,
                                    PurchaseOrder.PURCHASE_ORDER_SUBTYPE,
                                    PurchaseOrder.PURCHASE_ORDER_TYPE,
                                    PurchaseOrder.SUPPLIER,
                                    PurchaseOrder.LANGUAGE,
                                    PurchaseOrder.PURCHASING_ORGANIZATION,
                                    PurchaseOrder.PURCHASING_GROUP,
                                    PurchaseOrder.DOCUMENT_CURRENCY,
                                    PurchaseOrder.PURCHASE_ORDER_DATE,
                                    PurchaseOrder.PURCHASING_COMPLETENESS_STATUS,
                                    PurchaseOrder.PURCHASING_PROCESSING_STATUS )
                            .filter(PurchaseOrder.PURCHASE_ORDER.eq("4500001408"))
                            .execute(erpConfigContext);
        }catch (ODataException e){
            logger.info("the purchaser order isn't existed or not right");
        }
        return purchaseOrder.toString();
    }

    @Override
    public  String getPurchase(Logger logger) {
        logger.info("ALLOW_MOCKED_AUTH_HEADER: " + System.getenv("ALLOW_MOCKED_AUTH_HEADER"));
        logger.info("destinations: " + System.getenv("destinations"));

        String destinations = "ErpQueryEndpoint";
        ErpConfigContext erpConfigContext = new ErpConfigContext("ErpQueryEndpoint");
        List<PurchaseOrder> purchaseOrder = null;
        logger.info("erpConfigContext info:"+erpConfigContext.toString());
        try {
            purchaseOrder =
                    new DefaultPurchaseOrderService().getAllPurchaseOrder()
                            .select( PurchaseOrder.PURCHASE_ORDER,
                                    PurchaseOrder.COMPANY_CODE,
                                    PurchaseOrder.PURCHASE_ORDER_SUBTYPE,
                                    PurchaseOrder.PURCHASE_ORDER_TYPE,
                                    PurchaseOrder.SUPPLIER,
                                    PurchaseOrder.LANGUAGE,
                                    PurchaseOrder.PURCHASING_ORGANIZATION,
                                    PurchaseOrder.PURCHASING_GROUP,
                                    PurchaseOrder.DOCUMENT_CURRENCY,
                                    PurchaseOrder.PURCHASE_ORDER_DATE,
                                    PurchaseOrder.PURCHASING_COMPLETENESS_STATUS,
                                    PurchaseOrder.PURCHASING_PROCESSING_STATUS )
                            .filter(PurchaseOrder.PURCHASE_ORDER.eq("4500001408"))
                            .execute(erpConfigContext);
        }catch (ODataException e){
            logger.info("the purchaser order isn't existed or not right");
        }
        return purchaseOrder.toString();
    }

}
