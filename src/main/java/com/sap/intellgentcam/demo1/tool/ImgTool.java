package com.sap.intellgentcam.demo1.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/16  1:50 下午
 */
public class ImgTool {

    public static boolean Base64ToImage(String imgStr,String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片
        if (isEmpty(imgStr)) {
            return false;
        }
        try {
            byte[] b = Base64.decodeBase64(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * check the String if not null
     *
     * @param input
     * @return
     */
    private static boolean isEmpty(String input) {
        return input == null || input.equals("");
    }

    public static String transferToBase64(String path) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            return Base64.encodeBase64String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
