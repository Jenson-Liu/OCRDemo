package com.sap.intellgentcam.demo1.tool;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/18  10:58 上午
 */
public class ApiTool {

    public static final String TAG = "UploadHelper";
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private final OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static String getSessionToken() throws IOException {
        OkHttpClient client = new OkHttpClient();
        HashMap<String,String> hashMap = new HashMap<>();
        String Username = "sb-93550885-3efb-40d2-8b9d-621e84bae4d9!b25998|foundation-std-mlftrial!b3410";
        String Password = "yB0AivKOelaF0dSA4YLk2reDAW4=";
        String credential = Credentials.basic(Username,Password);

        Request request = new Request.Builder()
                .url("https://i501695trial.authentication.eu10.hana.ondemand.com/oauth/token?grant_type=client_credentials")
                .header("Authorization",credential)
                .addHeader("token","")
                .addHeader("clientid", "sb-93550885-3efb-40d2-8b9d-621e84bae4d9!b25998|foundation-std-mlftrial!b3410")
                .addHeader("appname", "93550885-3efb-40d2-8b9d-621e84bae4d9!b25998|foundation-std-mlftrial!b3410")
                .addHeader("identityzone", "i501695trial")
                .addHeader("identityzoneid", "825791ef-9fc1-4829-80a0-1d65ff1f68af")
                .addHeader("clientsecret", "yB0AivKOelaF0dSA4YLk2reDAW4=")
                .addHeader("tenant_id", "i501695trial")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("server error: " + response);
        }
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.body().string(),JsonObject.class);
        return jsonObject.get("access_token").getAsString();
    }

    /**
     * transfer img to order num
     * @param fileName
     * @param access_token
     * @return
     * @throws IOException
     */
    public static String testToken(String fileName,String access_token) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = "https://mlftrial-fs-ocr.cfapps.eu10.hana.ondemand.com/api/v2/image/ocr";
        File file =  new File(fileName);
        RequestBody fileBody = RequestBody.create(MEDIA_TYPE_PNG,file);
        access_token = "Bearer "+access_token;
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("files","a.png",fileBody)
                .addFormDataPart("lang","en")
                .addFormDataPart("outputType","txt")
                .addFormDataPart("pageSegMode","1")
                .addFormDataPart("modelType","lstmStandard")
                .addFormDataPart("textExtractionMode","0")
                .build();
        Request request = new Request.Builder()
                .header("Authorization",access_token)
                .url(url)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        String jsonString = response.body().string();
        return jsonString;
    }

}
