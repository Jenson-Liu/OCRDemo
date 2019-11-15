package com.sap.intellgentcam.demo1.tool;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/22  5:03 下午
 */
public class ApiToolTest {

    @Test
    public void testApi() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String key = "APIKey";
        String value = "DLT385hy4A7GA4qEQkAm37BttUyEADWV";
        String credential = Credentials.basic(key,value);
        String url = "https://sandbox.api.sap.com/s4hanacloud/sap/opu/odata/sap/API_PURCHASEORDER_PROCESS_SRV/A_PurchaseOrder";
        url += "(\'"+ "4500007924" + "\')";
        Request request = new Request.Builder()
                .url(url)
                .header("APIKey","DLT385hy4A7GA4qEQkAm37BttUyEADWV")
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("server error: " + response);
        }
        System.out.println(response.body().string());
    }

}
