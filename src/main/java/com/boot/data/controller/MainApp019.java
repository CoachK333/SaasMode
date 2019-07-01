package com.boot.data.controller;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.boot.data.controller.Poi4WordUtils.*;

/**
 * @author 98548
 * @create 2019-06-12 14:33
 * @description
 */
public class MainApp019 {
    public static void main(String[] args) throws IOException {
        String content = "夜班、加班、双休日业务值班、节假日值班明细";

        XWPFDocument doc = new XWPFDocument();

        XWPFTable table = doc.createTable(4, 6);
        setTableDefaultCellHeight(table, 561);
        table.setWidth(9036);
        XWPFTableRow row;
        XWPFTableCell cell;

        table.removeInsideHBorder();
        table.removeInsideVBorder();

        row = table.getRow(0);
        row.setHeight(561);
        cell = row.getCell(0);
        fillCell(cell, content, "华文仿宋", STVerticalJc.TOP, STJc.CENTER, true, 16, 1293, -1);
        addNewMerge(table, 0, 0, 0, 5);

        content = "（     年  月  日-    年  月   日）";
        row = table.getRow(1);
        row.setHeight(402);
        cell = row.getCell(0);
        fillCell(cell, content, "华文仿宋", STVerticalJc.CENTER, STJc.CENTER, false, 12, 1293, -1);
        addNewMerge(table, 1, 1, 0, 5);

        content = "填写单位：";
        row = table.getRow(2);
        row.setHeight(561);
        cell = row.getCell(0);
        fillCell(cell, content, "华文仿宋", STVerticalJc.CENTER, STJc.LEFT, false, 12, 1293, -1);
        addNewMerge(table, 2, 2, 0, 1);
        fillCell(row.getCell(3), "", "华文仿宋", STVerticalJc.CENTER, STJc.LEFT, false, 12, 1293, -1);
        content = "填报日期：   年  月    日";
        cell = row.getCell(3);
        fillCell(cell, content, "华文仿宋", STVerticalJc.CENTER, STJc.LEFT, false, 12, 1293, -1);
        addNewMerge(table, 2, 2, 3, 5);

        table.getRow(3).setHeight(561);
        table.removeBorders();

        table = doc.createTable(12, 6);
        setTableDefaultCellHeight(table, 561);
        setColumnWidth(table, 0, 1291);
        setColumnWidth(table, 1, 1418);
        setColumnWidth(table, 2, 1418);
        setColumnWidth(table, 3, 2834);
        setColumnWidth(table, 4, 879);
        setColumnWidth(table, 5, 1196);

        row = table.getRow(0);
        cell = row.getCell(0);
        content = "姓名";
        fillCell(cell, content, "华文仿宋", STVerticalJc.CENTER, STJc.CENTER, false, 12, 1293, -1);
        cell = row.getCell(1);
        content = "夜班/加班/双休日业务值班/节假 日值班";
        fillCell(cell, content, "华文仿宋", STVerticalJc.CENTER, STJc.CENTER, false, 12, 1293, -46);
        cell = row.getCell(2);
        content = "天数/时数";
        fillCell(cell, content, "华文仿宋", STVerticalJc.CENTER, STJc.CENTER, false, 12, 1293, -1);
        cell = row.getCell(3);
        content = "时段及具体任务明细";
        fillCell(cell, content, "华文仿宋", STVerticalJc.CENTER, STJc.CENTER, false, 12, 1293, -1);
        cell = row.getCell(4);
        content = "金额（元）";
        fillCell(cell, content, "华文仿宋", STVerticalJc.CENTER, STJc.CENTER, false, 12, 939, -1);
        cell = row.getCell(5);
        content = "备注";
        fillCell(cell, content, "华文仿宋", STVerticalJc.CENTER, STJc.CENTER, false, 12, 1293, -1);

        row = table.getRow(11);
        row.setHeight(561);
        cell = row.getCell(0);
        content = "合计";
        fillCell(cell, content, "华文仿宋", STVerticalJc.CENTER, STJc.CENTER, false, 12, 1293, -1);
        addNewMerge(table, 11, 11, 0, 3);

        table = doc.createTable(3, 6);
        setTableDefaultCellHeight(table, 561);
        row = table.getRow(1);
        cell = row.getCell(1);
        content = "填表人签字：";
        fillCell(cell, content, "华文仿宋", STVerticalJc.CENTER, STJc.LEFT, false, 12, 1293, -1);
        addNewMerge(table, 1, 1, 1, 2);
        cell = row.getCell(3);
        content = "本单位主要负责人签字：";
        fillCell(cell, content, "华文仿宋", STVerticalJc.CENTER, STJc.LEFT, false, 12, 1293, -1);
        addNewMerge(table, 1, 1, 3, 5);
        table.removeBorders();

        long l = System.currentTimeMillis();

        File file = new File("tempFiles/words/temp_" + l + ".docx");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream out = new FileOutputStream(file);
        doc.write(out);
        out.close();
        System.out.println(file.getName());
    }
}
