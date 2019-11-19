package com.sap.intellgentcam.demo1.serviceimp;

import com.sap.intellgentcam.demo1.service.PostGoodsReceiptService;
import com.sap.intellgentcam.demo1.service.RedisService;
import com.sap.intellgentcam.demo1.tool.AuthTool;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/29  10:08 上午
 */
@Service
public class PostGoodsReceiptServiceImpl implements PostGoodsReceiptService {

    private static final Logger logger = LoggerFactory.getLogger(PostGoodsReceiptServiceImpl.class );
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Autowired
    RedisService redisService;

    @Override
    public String getCsrfToken() {
        OkHttpClient client = new OkHttpClient();
        String url = "https://ccf-715.wdf.sap.corp/sap/opu/odata/sap/API_INBOUND_DELIVERY_SRV";
        HashMap<String,String> hashMap = AuthTool.getAuth();
        String Username = hashMap.get("username");
        String Password = hashMap.get("password");
        String credential = Credentials.basic(Username,Password);
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization",credential)
                .addHeader("x-csrf-token","Fetch")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            logger.info(String.valueOf(response.isSuccessful()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String Token = response.header("set-cookie")+";";
        Token += Token + ";" + response.header("x-csrf-token");
        logger.info("token:"+Token);
        redisService.setcsrfToken(Token);
        return Token;
    }

    @Override
    public boolean putAway(String deliveryNum,String token) throws IOException{
        logger.info("deliveryNum:"+deliveryNum);
        OkHttpClient client = new OkHttpClient.Builder().build();
        String url = " https://ccf-715.wdf.sap.corp/sap/opu/odata/sap/API_INBOUND_DELIVERY_SRV;v=0002/ConfirmPutawayAllItems?DeliveryDocument=";
        url +="\'"+deliveryNum+"\'";
        HashMap<String,String> hashMap = AuthTool.getAuth();
        String Username = hashMap.get("username");
        String Password = hashMap.get("password");
        String credential = Credentials.basic(Username,Password);
        RequestBody requestBody = RequestBody.create(null, new byte[]{});
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization",credential)
                .addHeader("x-csrf-token",token.split(";;")[1])
                .addHeader("cookie",token.split(";;")[0])
                .addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            logger.info("putAway success:"+response.isSuccessful());
            logger.info("putAway body:"+response.body().string());
            logger.info("putAway message:"+response.message());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        boolean result = true;
//        try{
//            response = client.newCall(request).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        logger.info(String.valueOf(response.isSuccessful()));
//        logger.info(response.body().string());
//        result = ;
//        logger.info("putAway success:"+result);
        return response.isSuccessful();
    }

    @Override
    public String getEtagMatch(String deliveryNum,String token) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        String url = "https://ccf-715.wdf.sap.corp/sap/opu/odata/sap/API_INBOUND_DELIVERY_SRV;v=0002/A_InbDeliveryHeader";
        url +="(\'"+deliveryNum+"\')";
        HashMap<String,String> hashMap = AuthTool.getAuth();
        String Username = hashMap.get("username");
        String Password = hashMap.get("password");
        String credential = Credentials.basic(Username,Password);
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization",credential)
                .addHeader("If-Match","*")
                .addHeader("x-csrf-token",token.split(";;")[1])
                .addHeader("cookie",token.split(";;")[0])
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("getEtagMatch:"+String.valueOf(response.isSuccessful()));
        return response.header("etag");
    }

    @Override
    public String postGoodsReceipt(String deliveryNum,String token, String match) {
        OkHttpClient client =  new OkHttpClient().newBuilder().connectTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .build();
        String url = "https://ccf-715.wdf.sap.corp/sap/opu/odata/sap/API_INBOUND_DELIVERY_SRV;v=0002/PostGoodsReceipt?DeliveryDocument=";
        url +="\'"+deliveryNum+"\'";
        HashMap<String,String> hashMap = AuthTool.getAuth();
        String Username = hashMap.get("username");
        String Password = hashMap.get("password");
        String credential = Credentials.basic(Username,Password);
        RequestBody requestBody = RequestBody.create(null, new byte[]{});
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization",credential)
                .addHeader("If-Match",match)
                .addHeader("x-csrf-token",token.split(";;")[1])
                .addHeader("cookie",token.split(";;")[0])
                .addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean result = response.isSuccessful();
        logger.info("PGR:"+result);
        logger.info("PGR Info:"+response.body());

        if(result == true){
            return "Post Goods Receipt Successfully";
        }else {
            return "Unable to Post Goods Receipt";
        }
    }
}
