package com.boot.data.controller;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * @author 98548
 * @create 2019-06-20 16:07
 * @description
 */
public class MainApp018 {
    public static void main(String[] args) throws IOException {
//        test01();
        test02();

    }

    private static void test02() throws IOException {
        XWPFDocument doc = new XWPFDocument();
        XWPFTable table = doc.createTable(5, 5);
        XWPFTableRow row = table.getRow(0);
        XWPFTableCell cell = row.getCell(0);
        XWPFParagraph paragraph = cell.addParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("测试换行符号");
        run.setText("测试换行符号123");

        long l = System.currentTimeMillis();
        File file = new File("word\\temp_" + l + ".docx");
        FileOutputStream out = new FileOutputStream(file);
        doc.write(out);
        out.close();
        System.out.println(file.getName());
    }

    private static void test01() throws IOException {
        XWPFDocument doc = new XWPFDocument();

        XWPFParagraph docParagraph = doc.createParagraph();
        docParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun docRun = docParagraph.createRun();
        docRun.setFontFamily("华文仿宋");
        docRun.setText("看看前面有没有换行!");

        XWPFTable table = doc.createTable(4, 4);
        CTTblPr tblPr = table.getCTTbl().getTblPr();
        tblPr.getTblW().setType(STTblWidth.DXA);
        tblPr.getTblW().setW(BigInteger.valueOf(10000));

        XWPFTableRow row = table.getRow(0);
        row.setHeight(1690);


        List<XWPFTableCell> cells = row.getTableCells();
        for (XWPFTableCell cell : cells) {
            cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
            cell.setParagraph(docParagraph);
//            XWPFParagraph paragraph = cell.addParagraph();
//
//            XWPFRun run = paragraph.createRun();
//            run.setFontFamily("华文仿宋");
////            run.addBreak(BreakClear.NONE);
//            run.setText("测试字体");
        }
        long l = System.currentTimeMillis();
        File file = new File("word\\temp_" + l + ".docx");
        FileOutputStream out = new FileOutputStream(file);
        doc.write(out);
        out.close();
        System.out.println(file.getName());
    }
}
