package com.sap.intellgentcam.demo1.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
            // Base64解码
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
     * 验证字符串是否为空
     *
     * @param input
     * @return
     */
    private static boolean isEmpty(String input) {
        return input == null || input.equals("");
    }
}
