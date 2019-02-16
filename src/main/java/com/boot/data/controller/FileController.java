package com.boot.data.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/file")
@Slf4j
public class FileController {

    @GetMapping("/download")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "dalaoyang.jpeg";// 文件名
        if (fileName != null) {
            //设置文件路径
            File file = new File("/Users/dalaoyang/Documents/dalaoyang.jpeg");
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }

    @PostMapping("/upload")
    public String upload(MultipartFile file) throws IOException {
        System.out.println("文件上上传中......");
        if (file.isEmpty()) {
            return "error:  file is empty";
        }
        String originalFilename = file.getOriginalFilename();
        if (!(originalFilename.endsWith("xls") || originalFilename.endsWith("xlsx"))) {
            return "error:  Incorrect file format";
        }
        Workbook workbook = null;
        try {
            FileInputStream fis = new FileInputStream(originalFilename);
            if (originalFilename.endsWith("xls")) {
                workbook = new HSSFWorkbook(fis);

            } else if (originalFilename.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(fis);
            }
            Sheet sheet001 = workbook.getSheetAt(0);

            int j0 = sheet001.getFirstRowNum();
            int j_end = sheet001.getLastRowNum();

            Row row001 = sheet001.getRow(0);
            final String dateStr = row001.getCell(0).getStringCellValue();
            String year = dateStr.substring(0, 4);
            String month = dateStr.substring(4, 6);
            String day = row001.getCell(1).getStringCellValue();
            LocalDate startDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            Set<String> names = new HashSet<>();
            Set<String> types = new HashSet<>();

            short i0 = row001.getFirstCellNum();
            short i7 = row001.getLastCellNum();
            int count = 0;

            for (int j = j0; j <= j_end; j++) {
                switch (j) {
                    case 0:
                        break;
                    case 1:
                        break;
                    default:
                        Row row = sheet001.getRow(j);
                        String name = null;
                        LocalDate date = startDate;
                        for (short i = i0; i < i7; i++) {
                            switch (i) {
                                case 0:
                                    name = row.getCell(i).getStringCellValue();
                                    name = StringUtils.remove(name, " ");
                                    names.add(name);
                                    break;
                                default:
                                    String cellValue = row.getCell(i).getStringCellValue();
                                    if (StringUtils.isNotBlank(cellValue) && !"0".equals(cellValue)) {

                                        System.out.println(name + ":  " + cellValue + "(" + date + ")");
                                        types.add(cellValue);
                                        count++;
                                    }
                                    date = date.plusDays(1);
                                    break;
                            }
                        }
                        break;
                }
            }
            System.out.println(count);
            System.out.println(names);
            System.out.println(types);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workbook.close();
        }


        return "ok";
    }

}

