package com.boot.data.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 98548
 * @create 2019-06-12 14:33
 * @description
 */
public class MainApp017 {
    public static void main(String[] args) throws IOException {
        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph para;
        XWPFRun run;

        //第一行
        String content = "加班申请表";
        para = doc.createParagraph();
        para.setAlignment(ParagraphAlignment.CENTER);
        para.setVerticalAlignment(TextAlignment.TOP);
        para.setIndentationLeft(2);
        run = para.createRun();
        run.setFontFamily("华文仿宋");
        run.setFontSize(18);
        run.setBold(true);
        run.setText(content);
        run.setTextPosition(28);

        //第二行
        para = doc.createParagraph();
        para.setAlignment(ParagraphAlignment.CENTER);
        para.setIndentationLeft(2);
        run = para.createRun();
        run.setText("                                         申请时间：    年    月    日");
        run.setFontFamily("华文仿宋");
        run.setFontSize(12);//小四

        XWPFTable table = doc.createTable(17, 4);
        XWPFTableRow row;
        XWPFTableCell cell;

//        table.setWidth(138 * 15 * 4);
//        table.getCTTbl().addNewTblPr().addNewTblLayout().setType(STTblLayoutType.FIXED);

        row = table.getRow(0);
        row.setHeight(563);
        cell = row.getCell(0);
        fillCell(cell, "申请人", "华文仿宋", ParagraphAlignment.CENTER, TextAlignment.CENTER, false, 12, 1711);

        table.getRow(1).setHeight(563);

        table.getRow(2).setHeight(563);
        addNewMerge(table, 0, 2, 0, 0);
        addNewMerge(table, 0, 2, 1, 3);

        row = table.getRow(3);
        row.setHeight(563);
        cell = row.getCell(0);
        fillCell(cell, "加班时间", "华文仿宋", ParagraphAlignment.CENTER, TextAlignment.CENTER, false, 12, 1711);
        addNewMerge(table, 3, 4, 0, 0);
        XWPFTableCell row02Cell02 = row.getCell(1);
        fillCell(row02Cell02, "起", "华文仿宋", ParagraphAlignment.CENTER, TextAlignment.CENTER, false, 12, 2422);

        XWPFTableCell row02Cell03 = row.getCell(2);
        fillCell(row02Cell03, "止", "华文仿宋", ParagraphAlignment.CENTER, TextAlignment.CENTER, false, 12, 2422);

        XWPFTableCell row02Cell04 = row.getCell(3);
        fillCell(row02Cell04, "时长（小时）", "华文仿宋", ParagraphAlignment.CENTER, TextAlignment.CENTER, false, 12, 2422);


        XWPFTableRow row03 = table.getRow(4);
        row03.setHeight(563);
        XWPFTableCell row03Cell001 = row03.getCell(1);
        fillCell(row03Cell001, "  月  日  时", "华文仿宋", ParagraphAlignment.CENTER, TextAlignment.CENTER, false, 12, 2422);
        XWPFTableCell row03Cell002 = row03.getCell(2);
        fillCell(row03Cell002, "  月  日  时", "华文仿宋", ParagraphAlignment.CENTER, TextAlignment.CENTER, false, 12, 2422);

        XWPFTableRow row04 = table.getRow(5);
        row04.setHeight(563);
        XWPFTableCell row04Cell01 = row04.getCell(0);
        fillCell(row04Cell01, "加班类别", "华文仿宋", ParagraphAlignment.CENTER, TextAlignment.CENTER, false, 12, 1711);

        XWPFTableCell row04Cell02 = row04.getCell(1);
        fillCell(row04Cell02, "□工作日   □双休日   □节假日", "华文仿宋", ParagraphAlignment.CENTER, TextAlignment.CENTER, false, 12, 2422);

        addNewMerge(table, 5, 5, 1, 3);

        XWPFTableRow row05 = table.getRow(6);
        row05.setHeight(563);
        XWPFTableCell row05Cell01 = row05.getCell(0);
        fillCell(row05Cell01, "加班内容", "华文仿宋", ParagraphAlignment.CENTER, TextAlignment.CENTER, false, 12, 1711);

        addNewMerge(table, 6, 14, 0, 0);
        addNewMerge(table, 6, 14, 1, 3);

        XWPFTableRow row06 = table.getRow(15);
        row06.setHeight(563);
        XWPFTableCell row06Cell01 = row06.getCell(0);
        fillCell(row06Cell01, "科长或秘书", "华文仿宋", ParagraphAlignment.CENTER, TextAlignment.CENTER, false, 12, 1711);

        addNewMerge(table, 15, 16, 0, 0);
        addNewMerge(table, 15, 16, 1, 1);
        XWPFTableCell row06Cell02 = row06.getCell(2);
        fillCell(row06Cell02, "本单位主要负责人\n" +
                "签字：\n", "华文仿宋", ParagraphAlignment.CENTER, TextAlignment.CENTER, false, 12, 2422);

        addNewMerge(table, 15, 16, 2, 2);
        addNewMerge(table, 15, 16, 3, 3);

        para = doc.createParagraph();
        para.setAlignment(ParagraphAlignment.LEFT);
        para.setIndentationFirstLine(2);
        para.setIndentationLeft(2);
        run = para.createRun();
        run.setText("注：申请人可填写多人");
        run.setFontFamily("华文仿宋");
        run.setFontSize(9);
        run.setBold(true);


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

    private static void test001() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        list = list.stream().filter(a -> (1 == a || 2 == a)).collect(Collectors.toList());
        System.out.println(list);
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

    private static void setValueWithStyle(XWPFTableCell cell, String text, String font, int fontSize, boolean boldFlag, String color, XWPFTableCell.XWPFVertAlign vertAlign, ParagraphAlignment horizontalAlignment) {
        //设置竖直居中
        cell.setVerticalAlignment(vertAlign);
        XWPFParagraph paragraph = cell.addParagraph();
        XWPFRun run = paragraph.createRun();
        paragraph.setAlignment(horizontalAlignment);
        String[] strings = text.split("\r\n");
        for (String string : strings) {
            run.setText(string);
            run.addBreak();
        }
        run.setFontFamily(font);
        run.setFontSize(fontSize);
        run.setBold(boldFlag);
        if (StringUtils.isNotBlank(color)) {
            run.setColor(color);
        }
    }

    /**
     * 设置单元格内容水平、垂直对齐方式
     *
     * @param cells      单元格s
     * @param stJc       水平方式
     * @param verticalJc 垂直方式
     */
    private static void applyCellStyleHorizontalAlignment(List<XWPFTableCell> cells, STJc.Enum stJc, STVerticalJc.Enum verticalJc) {
        cells.forEach(cell -> {
            CTTc ctTc = cell.getCTTc();
            ctTc.getPList().get(0).addNewPPr().addNewJc().setVal(stJc);
            ctTc.addNewTcPr().addNewVAlign().setVal(verticalJc);
        });

    }

    private static void addNewMerge(XWPFTable table, int firstRow, int lastRow, int firstCol, int lastCol) {
        for (int i = firstRow; i <= lastRow; i++) {
            XWPFTableRow row = table.getRow(i);
            row.setHeight(563);
            for (int j = firstCol; j <= lastCol; j++) {
                XWPFTableCell cell = row.getCell(j);
                if (j == firstCol) {
                    cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
                } else {
                    cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
                }
            }
        }

        for (int i = firstRow; i <= lastRow; i++) {
            XWPFTableRow row = table.getRow(i);
            if (i == firstRow) {
                row.getCell(firstCol).getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                row.getCell(firstCol).getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }
}
