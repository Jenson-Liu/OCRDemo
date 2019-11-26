package com.sap.intellgentcam.demo1.serviceimp;

import com.sap.intellgentcam.demo1.service.PurchaseServiceApi;
import com.sap.intellgentcam.demo1.tool.AuthTool;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * @author : Jenson.Liu
 * @date : 2019/11/22  5:03 下午
 */
@Service
public class PurchaseServiceApiImpl implements PurchaseServiceApi {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseServiceApiImpl.class );

    @Override
    public HashMap<String, String> getPurchase(String poNum) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = "https://ccf-715.wdf.sap.corp/sap/opu/odata/sap/API_PURCHASEORDER_PROCESS_SRV/A_PurchaseOrder(\'";
        url += poNum + "\')";
        HashMap<String,String> hashMap = AuthTool.getAuth();
        String Username = hashMap.get("username");
        String Password = hashMap.get("password");
        String credential = Credentials.basic(Username,Password);
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization",credential)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            logger.info(String.valueOf(response.isSuccessful()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = response.body().string();

        return null;
    }


}
