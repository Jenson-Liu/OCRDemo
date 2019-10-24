package com.sap.intellgentcam.demo1.tool;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author : Jenson.Liu
 * @date : 2019/10/16  11:20 上午
 */
public class FileTool {

    public static File MultipartFiletoFile(MultipartFile multipartFile) {
        File file = null;
        InputStream ins = null;
        try {
            ins = multipartFile.getInputStream();
            file = new File(multipartFile.getOriginalFilename());
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File del = new File(file.toURI());
        return del;
    }

}
