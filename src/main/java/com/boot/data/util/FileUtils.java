package com.boot.data.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 98548
 * @create 2019-04-17 17:02
 * @description
 */
public class FileUtils {

    public static Map<String, String> upload(MultipartFile file) {

        String fileName = System.currentTimeMillis() + file.getOriginalFilename();

        String savePath = "c:/temp_file/" + fileName;
        File dest = new File(savePath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try (InputStream inputStream = file.getInputStream(); FileOutputStream outputStream = new FileOutputStream(dest)) {

            byte[] bytes = new byte[1024];
            int length;
            while ((length = inputStream.read(bytes, 0, bytes.length)) != -1) {
                outputStream.write(bytes, 0, length);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> map = new HashMap<>();
        map.put("filename", dest.getName());
        map.put("filePath", dest.getAbsolutePath());

        return map;
    }
}
