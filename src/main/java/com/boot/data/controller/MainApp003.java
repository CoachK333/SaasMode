package com.boot.data.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 98548
 * @create 2018-12-27 11:08
 * @description poi生成word
 */
public class MainApp003 {

    public static void main(String[] args) throws IOException {
        word();

    }

    private static String word() throws IOException {
        XWPFDocument document = new XWPFDocument();
        String fileName = "temp_" + System.currentTimeMillis() + ".doc";
        FileOutputStream outputStream = new FileOutputStream(fileName);

        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        CTPageSz pgSz = sectPr.addNewPgSz();
        pgSz.setW(BigInteger.valueOf(16840));
        pgSz.setH(BigInteger.valueOf(11907));
        pgSz.setOrient(STPageOrientation.LANDSCAPE);

        CTPageMar pageMar = sectPr.addNewPgMar();
        pageMar.setLeft(BigInteger.valueOf(720L));
        pageMar.setRight(BigInteger.valueOf(720L));
        pageMar.setTop(BigInteger.valueOf(720L));
        pageMar.setBottom(BigInteger.valueOf(992L));

        XWPFParagraph titleParagraph = document.createParagraph();

        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = titleParagraph.createRun();
        CTRPr ctrPr = run.getCTR().addNewRPr();
        CTFonts ctFonts = ctrPr.addNewRFonts();
        ctFonts.setEastAsia("宋体");
        run.setText("国家气象信息中心职工考勤登记表");
        run.setFontSize(16);
        run.setBold(true);
        run.addBreak();

        XWPFParagraph paragraph = document.createParagraph();
        setFontWithFormat(paragraph, "单位:", 12, "宋体", true, ParagraphAlignment.LEFT, false);
        setFontWithFormat(paragraph, "资料服务室                                                                          ", 12, "宋体", false, ParagraphAlignment.LEFT, false);
        setFontWithFormat(paragraph, "2018年07月21日至2018年08月20日", 12, "宋体", true, ParagraphAlignment.LEFT, true);

        List<String> list = new ArrayList(31);
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        int size = list.size();
        XWPFTable table;
        if (size < 14) {
            table = document.createTable(14 + 1, 35);
        } else {
            table = document.createTable(size * 2 + 1/*行数*/, 35/*列数*/);
        }
        setTableWidth(table, "15469");
        setTableWidth(table, "15400");
        XWPFTableRow row001 = table.getRow(0);
        XWPFTableCell row001Cell001 = row001.getCell(0);
        row001Cell001.setText("姓名");
        XWPFTableCell row001Cell002 = row001.getCell(1);
        row001Cell002.setText("日期");
        for (int i = 0; i < list.size(); i++) {
            XWPFTableRow row = table.getRow(i + 1);
            XWPFTableCell cell = row.getCell(0);
            cell.setText(list.get(i));
        }
        XWPFTableCell row001Cell004 = row001.getCell(31 + 2);
        row001Cell004.setText("备注");
        XWPFTableCell row001Cell005 = row001.getCell(31 + 3);
        row001Cell005.setText("123");

        XWPFTableRow row002 = table.getRow(1);
        row002.getCell(31 + 2).setText("公差");
        row002.getCell(31 + 3).setText("○");
        XWPFTableRow row003 = table.getRow(2);
        row003.getCell(31 + 2).setText("休假");
        row003.getCell(31 + 3).setText("△");
        XWPFTableRow row004 = table.getRow(3);
        row004.getCell(31 + 2).setText("疗养");
        row004.getCell(31 + 3).setText("L");
        XWPFTableRow row005 = table.getRow(4);
        row005.getCell(31 + 2).setText("公伤");
        row005.getCell(31 + 3).setText("G");
        XWPFTableRow row006 = table.getRow(5);
        row006.getCell(31 + 2).setText("婚丧");
        row006.getCell(31 + 3).setText("H");
        XWPFTableRow row007 = table.getRow(6);
        row007.getCell(31 + 2).setText("产假");
        row007.getCell(31 + 3).setText("C");
        XWPFTableRow row008 = table.getRow(7);
        row008.getCell(31 + 2).setText("探亲");
        row008.getCell(31 + 3).setText("T");
        XWPFTableRow row009 = table.getRow(8);
        row009.getCell(31 + 2).setText("病假");
        row009.getCell(31 + 3).setText("B");
        XWPFTableRow row010 = table.getRow(9);
        row010.getCell(31 + 2).setText("事假");
        row010.getCell(31 + 3).setText("S");
        XWPFTableRow row011 = table.getRow(10);
        row011.getCell(31 + 2).setText("迟到");
        row011.getCell(31 + 3).setText("W");
        XWPFTableRow row012 = table.getRow(11);
        row012.getCell(31 + 2).setText("早退");
        row012.getCell(31 + 3).setText("Z");
        XWPFTableRow row013 = table.getRow(12);
        row013.getCell(31 + 2).setText("旷工");
        row013.getCell(31 + 3).setText("K");
        XWPFTableRow row014 = table.getRow(13);
        row014.getCell(31 + 2).setText("培训");
        row014.getCell(31 + 3).setText("P");
        XWPFTableRow row015 = table.getRow(14);
        row015.getCell(31 + 2).setText("离职");
        row015.getCell(31 + 3).setText("D");

        mergeCellsHorizontal(table, 0, 33, 34);
        mergeCellsVertically(table, 0, 3, 4);

        System.out.println(table.getWidth());
        document.write(outputStream);
        return fileName;
    }

    private static void setFontWithFormat(XWPFParagraph paragraph, String content, int fontSize, String fontTypeStyle, boolean isBold, ParagraphAlignment horizontalPosition, boolean isAddBreak) {
        if (horizontalPosition != null) {
            paragraph.setAlignment(horizontalPosition);
        }

        XWPFRun run = paragraph.createRun();
        run.setText(content);
        run.setFontSize(fontSize);

        if (isAddBreak) {
            run.addBreak();
        }
        run.setBold(isBold);

        if (!StringUtils.isBlank(fontTypeStyle)) {
            CTFonts rFonts = run.getCTR().addNewRPr().addNewRFonts();
            rFonts.setEastAsia(fontTypeStyle);
            rFonts.setAscii("Times New Roman");
        }
    }

    // word跨列合并单元格
    public static void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if (cellIndex == fromCell) {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
            Integer width = (toCell - fromCell + 1) / 35 * table.getCTTbl().getTblPr().getTblW().getW().intValue();
            System.out.println(width);
            CTTblWidth ctTblWidth = cell.getCTTc().getTcPr().addNewTcW();
            ctTblWidth.setW(BigInteger.valueOf(width));
            ctTblWidth.setType(STTblWidth.DXA);
        }
    }

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

}
