package com.boot.data.util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 98548
 * @create 2018-12-29 15:20
 * @description
 */
public class PoiUtils {

    //选定区域全部加边框
    public static void setAllBorder(BorderStyle style, CellRangeAddress region, Sheet sheet) {
        int firstColumn = region.getFirstColumn();
        int lastColumn = region.getLastColumn();
        int firstRow = region.getFirstRow();
        int lastRow = region.getLastRow();
        for (int i = firstColumn; i <= lastColumn; i++) {
            for (int j = firstRow; j <= lastRow; j++) {
                RegionUtil.setBorderTop(style, new CellRangeAddress(j, j, 0, i), sheet);
                RegionUtil.setBorderBottom(style, new CellRangeAddress(j, j, 0, i), sheet);
                RegionUtil.setBorderLeft(style, new CellRangeAddress(j, j, 0, i), sheet);
                RegionUtil.setBorderRight(style, new CellRangeAddress(j, j, 0, i), sheet);
            }
        }
    }

    /**
     * 选定区域加样式
     *
     * @param sheet
     * @param style
     * @param region
     */
    public static void addStyle(Sheet sheet, CellStyle style, CellRangeAddress region) {
        int firstColumn = region.getFirstColumn();
        int lastColumn = region.getLastColumn();
        int firstRow = region.getFirstRow();
        int lastRow = region.getLastRow();
        for (int i = firstRow; i <= lastRow; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                row = sheet.createRow(i);
            }
            for (int j = firstColumn; j <= lastColumn; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }
                cell.setCellStyle(style);
            }
        }
    }

    /**
     * 选定区域加格式(数字/文本/时间)
     *
     * @param sheet
     * @param cellType
     * @param region
     */
    public static void addCellType(Sheet sheet, CellType cellType, CellRangeAddress region) {
        int firstColumn = region.getFirstColumn();
        int lastColumn = region.getLastColumn();
        int firstRow = region.getFirstRow();
        int lastRow = region.getLastRow();
        for (int i = firstRow; i <= lastRow; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                row = sheet.createRow(i);
            }
            for (int j = firstColumn; j <= lastColumn; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }
                cell.setCellType(cellType);
            }
        }
    }


    /**
     * 创建字体
     *
     * @param fontName 字体名称
     * @param fontSize 字体大小(单位:Points)
     * @param bold     是否加粗
     * @param workbook
     * @return
     */
    public static HSSFFont createFont(String fontName, Short fontSize, Boolean bold, HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints(fontSize);
        font.setBold(bold);
        return font;
    }
    /**
     * 创建字体
     *
     * @param fontName 字体名称
     * @param fontSize 字体大小(单位:Points)
     * @param bold     是否加粗
     * @param workbook
     * @return
     */
    public static HSSFFont createFontWithHeight(String fontName, Float fontSize, Boolean bold, HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setFontName(fontName);
        font.setFontHeight((short) (fontSize * 20));
        font.setBold(bold);
        return font;
    }

    /**
     * 创建样式
     *
     * @param fillForegroundColorIndex 前景色值
     * @param patternType              前景填充方式
     * @param horizontalAlignment      水平对齐方式
     * @param verticalAlignment        竖直对齐方式
     * @param font                     字体
     * @param wrapTextFlag             换行标识
     * @param workbook
     * @return
     */
    public static HSSFCellStyle createCellStyle(
            short fillForegroundColorIndex,
            FillPatternType patternType,
            HorizontalAlignment horizontalAlignment,
            VerticalAlignment verticalAlignment,
            HSSFFont font,
            boolean wrapTextFlag,
            HSSFWorkbook workbook) {
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        if (fillForegroundColorIndex >= 0) {
            cellStyle.setFillForegroundColor(fillForegroundColorIndex);
        }
        if (patternType != null) {
            cellStyle.setFillPattern(patternType);
        }
        if (horizontalAlignment != null) {
            cellStyle.setAlignment(horizontalAlignment);
        }
        if (verticalAlignment != null) {
            cellStyle.setVerticalAlignment(verticalAlignment);
        }
        if (font != null) {
            cellStyle.setFont(font);
        }
        cellStyle.setWrapText(wrapTextFlag);
        return cellStyle;
    }

    /**
     * @param sheet       sheet页
     * @param style       样式
     * @param value       内容
     * @param rowIndex    行标
     * @param columnIndex 列表
     */
    public static void setCellValueWithStyle(Sheet sheet, CellStyle style, String value, int rowIndex, int columnIndex) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }

        Cell cell = row.getCell(columnIndex);
        if (cell == null) {
            cell = row.createCell(columnIndex);
        }
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    public static void setCellValueWithFont(HSSFWorkbook workbook, Sheet sheet, HSSFFont font, String value, int rowIndex, int columnIndex) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        Cell cell = row.getCell(columnIndex);
        if (cell == null) {
            cell = row.createCell(columnIndex);
        }

        CellStyle cellStyle = cell.getCellStyle();
        cellStyle.setFont(font);

        cell.setCellValue(value);
        cell.setCellStyle(cellStyle);
    }

    /**
     * 根据内容寻找坐标
     *
     * @param sheet
     * @param value
     * @return
     */
    public static Map<Integer, Integer> getCoordinateByValue(Sheet sheet, String value) {
        Map<Integer, Integer> map = new HashMap<>();
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();

        for (int i = firstRowNum; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            short firstCellNum = row.getFirstCellNum();
            short lastCellNum = row.getLastCellNum();
            for (short j = firstCellNum; j <= lastCellNum; j++) {
                Cell cell = row.getCell(j);
                String s = cell.getStringCellValue().replaceAll(" ", "");
                if (s.equals(value)) {
                    map.put(i, (int) j);
                    return map;
                }
            }
        }
        return null;
    }

    /**
     * 根据姓名查找坐标
     *
     * @param sheet
     * @param name
     * @return
     */
    public static Map<Integer, Integer> getCoordinateByName(Sheet sheet, String name) {
        Map<Integer, Integer> map = new HashMap<>();
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();

        for (int i = firstRowNum; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0);
            String cellContent = cell.getStringCellValue().replaceAll(" ", "");
            if (cellContent.equals(name)) {
                map.put(i, 0);
                return map;
            }
        }
        return null;
    }

}
