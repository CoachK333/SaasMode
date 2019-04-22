package com.boot.data.controller;

import com.boot.data.util.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * @author 98548
 * @create 2019-04-17 16:59
 * @description
 */
@RestController
@RequestMapping("/fileOpera")
public class FileOperaController {

    @PostMapping("/upload")
    public String upload(HttpServletRequest request, MultipartFile file) {
        Map<String, String> fileInfo = FileUtils.upload(file);

        return fileInfo.toString();
    }


    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        File file = new File("C:\\Users\\98548\\Desktop\\weixin-mini-0.0.1-SNAPSHOT.jar");
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());// 设置文件名
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        byte[] buffer = new byte[10240];
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)); OutputStream os = response.getOutputStream()) {
            int i;
            while ((i = bis.read(buffer)) != -1) {
                os.write(buffer, 0, i);
            }
        } catch (IOException e) {
            throw new RuntimeException("文件下载失败!");
        }
    }
}
