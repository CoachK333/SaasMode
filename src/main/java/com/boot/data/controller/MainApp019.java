package com.boot.data.controller;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author 98548
 * @create 2019-06-12 14:33
 * @description
 */
public class MainApp019 {
    public static void main(String[] args) throws IOException {
        XWPFDocument doc = new XWPFDocument();
        XWPFTable table = doc.createTable(4, 4);
        XWPFTableRow row = table.getRow(0);
        XWPFTableCell cell = row.getCell(0);

        fillCell(cell, "申请人1", "华文仿宋", ParagraphAlignment.CENTER, TextAlignment.CENTER, false, 12, 1000);
        cell = row.getCell(1);
        fillCell(cell, "申请人2", "华文仿宋", ParagraphAlignment.CENTER, TextAlignment.CENTER, false, 12, 500);
        cell = row.getCell(2);
        fillCell(cell, "申请人3", "华文仿宋", ParagraphAlignment.CENTER, TextAlignment.CENTER, false, 12, 1500);


        long l = System.currentTimeMillis();
        File file = new File("word\\temp_" + l + ".docx");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream out = new FileOutputStream(file);
        doc.write(out);
        out.close();
        System.out.println(file.getName());

    }

    private static void fillCell(XWPFTableCell cell, String text, String font, ParagraphAlignment paragraphAlignment, TextAlignment textAlignment, boolean bold, int fontSize, int cellWidth) {

        CTTcPr cellPr = cell.getCTTc().addNewTcPr();
        cellPr.addNewTcW().setW(BigInteger.valueOf(cellWidth));
        XWPFParagraph para = cell.getParagraphs().get(0);
        para.setAlignment(paragraphAlignment);//文字对齐方式(左、右、中)
        para.setVerticalAlignment(textAlignment);
        XWPFRun run = para.createRun();
        run.setText(text);
        run.setBold(bold);//是否加粗
        run.setFontFamily(font);//字体类型
        run.setFontSize(fontSize);//小二
    }
}
