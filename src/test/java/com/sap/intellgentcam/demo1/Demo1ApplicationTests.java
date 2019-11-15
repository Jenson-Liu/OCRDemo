package com.sap.intellgentcam.demo1;

import java.io.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1ApplicationTests {



    @Test
    public void contextLoads() throws IOException {
        String requestUrl = "https://mlftrial-fs-ocr.cfapps.eu10.hana.ondemand.com/api/v2/image/ocr";
//        System.out.println(ApiTool.getToken());
    }
}
