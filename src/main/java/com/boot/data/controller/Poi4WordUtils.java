package com.boot.data.controller;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @author 98548
 * @create 2019-06-24 10:29
 * @description
 */
public class Poi4WordUtils {

    /**
     * 填充单元格数据(带样式的哈)
     *
     * @param cell
     * @param text
     * @param font
     * @param vAlign
     * @param hAlign
     * @param bold
     * @param fontSize
     * @param cellWidth
     */
    public static void fillCell(XWPFTableCell cell, String text, String font, STVerticalJc.Enum vAlign, STJc.Enum hAlign, boolean bold, int fontSize, int cellWidth,int textPosition) {

        CTTc ctTc = cell.getCTTc();
        CTTcPr cellPr = ctTc.addNewTcPr();
        cellPr.addNewTcW().setW(BigInteger.valueOf(cellWidth));
        cellPr.addNewVAlign().setVal(vAlign);//垂直对齐方式
        ctTc.getPList().get(0).addNewPPr().addNewJc().setVal(hAlign);
        XWPFParagraph para = cell.getParagraphs().get(0);
        XWPFRun run = para.createRun();
        run.setText(text);
        run.setBold(bold);//是否加粗
        run.setFontFamily(font);//字体类型
        run.setFontSize(fontSize);//字体大小
        run.setTextPosition(textPosition);
    }

    /**
     * 合并单元格区域
     *
     * @param table
     * @param firstRow
     * @param lastRow
     * @param firstCol
     * @param lastCol
     */
    public static void addNewMerge(XWPFTable table, int firstRow, int lastRow, int firstCol, int lastCol) {
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

    public static void setTableDefaultCellHeight(XWPFTable table, long height) {
        List<XWPFTableRow> rows = table.getRows();
        rows.forEach(
                xwpfTableRow -> {
                    CTTrPr ctTrPr = xwpfTableRow.getCtRow().addNewTrPr();
                    CTHeight ctHeight = ctTrPr.addNewTrHeight();
                    ctHeight.setVal(BigInteger.valueOf(height));
                }
        );
    }

    public static void setCellWidth(XWPFTableCell cell, long width) {
        setCellW(cell, width);
    }

    private static void setCellW(XWPFTableCell cell, long width) {
        CTTcPr ctTcPr = cell.getCTTc().addNewTcPr();
        CTTblWidth ctTblWidth = ctTcPr.addNewTcW();
        ctTblWidth.setType(STTblWidth.DXA);
        ctTblWidth.setW(BigInteger.valueOf(width));
    }

    public static void setColumnWidth(XWPFTable table, int column, long columnWidth) {
        table.getRows().forEach(xwpfTableRow -> {
            XWPFTableCell cell = xwpfTableRow.getCell(column);
            setCellW(cell, columnWidth);
        });
    }

}
