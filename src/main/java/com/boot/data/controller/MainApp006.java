package com.boot.data.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 98548
 * @create 2019-02-15 10:18
 * @description
 */
public class MainApp006 {

    public static void main(String[] args) throws IOException {
        formExcel(new File("C:\\Users\\98548\\Desktop\\新建 XLSX 工作表 (2).xls"));
    }

    private static void formExcel(File file) throws IOException {
        String filename = file.getName();
        Workbook workbook = null;
        if (filename.endsWith(".xls")) {
            workbook = new HSSFWorkbook(new FileInputStream(file));
        } else if (filename.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(new FileInputStream(file));
        }
        Font font006 = workbook.createFont();
        font006.setFontName("宋体");
        font006.setFontHeightInPoints((short) 9);
        font006.setBold(true);


        Sheet sheet001 = workbook.getSheetAt(0);
        int j0 = sheet001.getFirstRowNum();
        int j_end = sheet001.getLastRowNum();
        System.out.println("一共多少行:  " + j_end);
        for (int j = j0; j <= j_end; j++) {
            Row row = sheet001.getRow(j);
            short k0 = row.getFirstCellNum();
            short k_end = row.getLastCellNum();
            for (short k = k0; k < k_end; k++) {
                Cell cell = row.getCell(k);
                CellStyle cellStyle1 = workbook.createCellStyle();

                String cellValue = cell.getStringCellValue();
                if (cellValue != null && (cellValue.equals("○") || cellValue.equals("△"))) {
                    CellStyle cellStyle = cell.getCellStyle();
                    HorizontalAlignment alignment = cellStyle.getAlignment();
                    System.out.println(alignment);
                    VerticalAlignment verticalAlignment = cellStyle.getVerticalAlignment();
                    System.out.println(verticalAlignment);
                    FillPatternType fillPattern = cellStyle.getFillPattern();
                    System.out.println(fillPattern);
                    short fillForegroundColor = cellStyle.getFillForegroundColor();
                    System.out.println(fillForegroundColor);
                    BorderStyle borderTop = cellStyle.getBorderTop();
                    System.out.println(borderTop);
                    BorderStyle borderBottom = cellStyle.getBorderBottom();
                    System.out.println(borderBottom);
                    BorderStyle borderLeft = cellStyle.getBorderLeft();
                    System.out.println(borderLeft);
                    BorderStyle borderRights = cellStyle.getBorderRight();
                    System.out.println(borderRights);

                    cellStyle1.setAlignment(alignment);
                    cellStyle1.setVerticalAlignment(verticalAlignment);
                    if (fillForegroundColor != 64) {
                        IndexedColors[] values = IndexedColors.values();
                        String colorName = values[fillForegroundColor].name();
                        System.out.println("(" + (j + 1) + ", " + k + ")不是透明色,是" + colorName + "色");
                        cellStyle1.setFillForegroundColor(values[fillForegroundColor].getIndex());
                    }
                    cellStyle1.setFillPattern(fillPattern);
                    cellStyle1.setBorderTop(borderTop);
                    cellStyle1.setBorderBottom(borderBottom);
                    cellStyle1.setBorderLeft(borderLeft);
                    cellStyle1.setBorderRight(borderRights);
                    cellStyle1.setFont(font006);

                    cell.setCellStyle(cellStyle1);

                }
            }
            System.out.println("第" + (j + 1) + "行: " + k_end + "列");
        }
        workbook.write(new FileOutputStream(file));
    }
}
