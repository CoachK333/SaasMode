package com.boot.data.controller;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigInteger;

/**
 * @author 98548
 * @create 2019-01-03 18:15
 * @description
 */
public class Poi2WordApp {
    public static void main(String[] args) throws Exception {
        String fileName = "temp_" + System.currentTimeMillis() + ".doc";
        XWPFDocument document = new XWPFDocument();
        megerTableCell(document);
        saveDocument(document, fileName);
    }

    public static void megerTableCell(XWPFDocument document) {
        XWPFTable table1 = document.createTable(6, 8);
        setTableWidth(table1, "8000");
        fillTable(table1);
        mergeCellsVertically(table1, 1, 1, 4);
        mergeCellsVertically(table1, 4, 2, 4);
        mergeCellsHorizontal(table1, 0, 3, 5);
        mergeCellsHorizontal(table1, 2, 2, 3);
        mergeCellsHorizontal(table1, 2, 6, 7);
    }

    public static void fillTable(XWPFTable table) {
        for (int rowIndex = 0; rowIndex < table.getNumberOfRows(); rowIndex++) {
            XWPFTableRow row = table.getRow(rowIndex);
            row.setHeight(380);
            for (int colIndex = 0; colIndex < row.getTableCells().size(); colIndex++) {
                XWPFTableCell cell = row.getCell(colIndex);
                if (rowIndex % 2 == 0) {
                    setCellText(cell, " cell " + rowIndex + colIndex + " ", "D4DBED", 1000);
                } else {
                    setCellText(cell, " cell " + rowIndex + colIndex + " ", "AEDE72", 1000);
                }
            }
        }
    }

    public static void setCellText(XWPFTableCell cell, String text, String bgcolor, int width) {
        CTTc cttc = cell.getCTTc();
        CTTcPr cellPr = cttc.addNewTcPr();
        cellPr.addNewTcW().setW(BigInteger.valueOf(width));
        //cell.setColor(bgcolor);
        CTTcPr ctPr = cttc.addNewTcPr();
        CTShd ctshd = ctPr.addNewShd();
        ctshd.setFill(bgcolor);
        ctPr.addNewVAlign().setVal(STVerticalJc.CENTER);
        cttc.getPList().get(0).addNewPPr().addNewJc().setVal(STJc.CENTER);
        cell.setText(text);
    }


    /**
     * @Description: 跨列合并
     */
    public static void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if (cellIndex == fromCell) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    /**
     * @Description: 跨行合并
     * @see http://stackoverflow.com/questions/24907541/row-span-with-xwpftable
     */
    public static void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if (rowIndex == fromRow) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    public static void setTableWidth(XWPFTable table, String width) {
        CTTbl ttbl = table.getCTTbl();
        CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl.getTblPr();
        CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr.addNewTblW();
        CTJc cTJc = tblPr.addNewJc();
        cTJc.setVal(STJc.Enum.forString("center"));
        tblWidth.setW(new BigInteger(width));
        tblWidth.setType(STTblWidth.DXA);
    }

    public static void saveDocument(XWPFDocument document, String savePath)
            throws Exception {
        FileOutputStream fos = new FileOutputStream(savePath);
        document.write(fos);
        fos.close();
    }
}
