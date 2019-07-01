package com.boot.data.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author 98548
 * @create 2019-06-26 16:06
 * @description
 */
public class MainApp020 {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("C:\\Users\\98548\\Desktop\\准备删除的模板.docx");
        XWPFDocument doc = new XWPFDocument(inputStream);
        List<XWPFTable> tables = doc.getTables();

        XWPFTable table = tables.get(0);
        System.out.println("table.getWidth: " + table.getWidth());

        final int[] i = {0};

        List<XWPFTableRow> rows = table.getRows();
        rows.forEach(xwpfTableRow -> {

            xwpfTableRow.getTableCells().forEach(cell -> {
                if (StringUtils.isNotBlank(cell.getText())) {
                    System.out.println(cell.getText() + " : " + cell.getWidth());
                }
            });

            i[0]++;
        });

        XWPFTableRow row5 = rows.get(5);
        List<XWPFTableCell> cells = row5.getTableCells();
        cells.forEach(cell -> {
            System.out.println(cell.getWidth());
        });

        System.out.println("------------------------------");

        XWPFTableRow row6 = rows.get(6);

        row6.getTableCells().forEach(cell -> {
            System.out.println(cell.getWidth());
        });
    }
}
