package com.boot.data.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 98548
 * @create 2018-12-21 9:38
 * @description
 */
public class MainApp002 {


    public static void main(String[] args) throws Exception {
//        createPdfFile();
//        temp001();
//        addWaterMark("C:\\Users\\98548\\IdeaProjects\\hello-springboot\\temp_1545618379425.pdf", "C:\\Users\\98548\\IdeaProjects\\hello-springboot\\mark\\temp_1545618379425.pdf", "test001", 300, 50);
        waterMark("123.pdf", "456.pdf", "国家气象信息中心");

    }


    public static void createPdfFile() throws Exception {
        Document doc = new Document();
        FileOutputStream out = new FileOutputStream("temp_" + System.currentTimeMillis() + ".pdf");
        PdfWriter.getInstance(doc, out);
        doc.open();

        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font titleFont = new Font(bfChinese, 16, Font.BOLD);// 标题
        Font tableTitleFont = new Font(bfChinese, 12, Font.BOLD);// 表格标题
        Font conyentFont = new Font(bfChinese, 12, Font.NORMAL);// 内容

        Paragraph title = new Paragraph("受理材料收取凭证", titleFont);
        title.setAlignment(Rectangle.ALIGN_CENTER);// 居中
        doc.add(title);

        StringBuffer strBuff = new StringBuffer();
        strBuff.append("兹收到客户  " + "SAM-SHO");
        strBuff.append("（先生/女士）提交的保单号为    " + "123456789009876");
        strBuff.append("     的申请材料，共   " + " 2 " + "  张保单。");

        Paragraph content = new Paragraph(strBuff.toString(), conyentFont);
        content.setAlignment(Rectangle.ALIGN_JUSTIFIED);
        content.setFirstLineIndent(15f);// 首行缩进
        content.setSpacingBefore(30f);// 上留白
        doc.add(content);

        String cont2 = "申请保全项目    " + " CM-退保";
        content = new Paragraph(cont2, conyentFont);
        content.setAlignment(Rectangle.ALIGN_JUSTIFIED);
        content.setFirstLineIndent(15f);// 首行缩进
        content.setSpacingBefore(15f);// 上留白
        doc.add(content);

        String cont3 = "所提供材料明细如下：";
        content = new Paragraph(cont3, conyentFont);
        content.setAlignment(Rectangle.ALIGN_JUSTIFIED);
        content.setFirstLineIndent(15f);// 首行缩进
        content.setSpacingBefore(15f);// 上留白
        content.setSpacingAfter(15f);// 下留白
        doc.add(content);

        // 表格的处理是难点，特别是表格的跨行跨列
        // 可以使用跨行也可以使用表格嵌套
        int tCol = 5;
        PdfPTable table = new PdfPTable(tCol);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.setTotalWidth(500f);
        table.setWidths(new float[]{0.4f, 0.25f, 0.25f, 0.25f, 0.25f});
        table.setWidthPercentage(100);
        table.setLockedWidth(true);

        String strTableTitle = "申请材料名称";
        Paragraph tableTitle = new Paragraph(strTableTitle, tableTitleFont);
        PdfPCell cell = new PdfPCell(tableTitle);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
        cell.setRowspan(2);// 跨2行
        table.addCell(cell);

        strTableTitle = "申请材料类型";
        tableTitle = new Paragraph(strTableTitle, tableTitleFont);
        cell = new PdfPCell(tableTitle);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
        cell.setColspan(2);// 跨2列
        table.addCell(cell);

        strTableTitle = "收取页数/份数";
        tableTitle = new Paragraph(strTableTitle, tableTitleFont);
        cell = new PdfPCell(tableTitle);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
        cell.setRowspan(2);// 跨2行
        table.addCell(cell);

        strTableTitle = "备注";
        tableTitle = new Paragraph(strTableTitle, tableTitleFont);
        cell = new PdfPCell(tableTitle);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
        cell.setRowspan(2);// 跨2行
        table.addCell(cell);

        // 这边属于第二行的表格
        // 思路上，这点很关键
        strTableTitle = "原件";
        tableTitle = new Paragraph(strTableTitle, tableTitleFont);
        cell = new PdfPCell(tableTitle);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
        table.addCell(cell);

        strTableTitle = "复印件";
        tableTitle = new Paragraph(strTableTitle, tableTitleFont);
        cell = new PdfPCell(tableTitle);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
        table.addCell(cell);

        String[] arrTitle = {"保险合同", "保险合同收据或发票", "保单贷款申请书", "保全变更申请书", "被保险人变更清单", "健康及财务告知", "授权委托书", "投保人身份证件", "被保险人身份证件", "身故受益人身份证件", "受托人身份证件", "投保人账号", "被保险人/监护人账号 ", "生存证明", "关系证明 ",
                "其他受理材料 "};
        int tRow = arrTitle.length;

        for (int i = 0; i < tRow; i++) {
            for (int j = 0; j < tCol; j++) {
                if (j == 0) {
                    // 左侧标题
                    strTableTitle = arrTitle[i];
                    tableTitle = new Paragraph(strTableTitle, conyentFont);
                    cell = new PdfPCell(tableTitle);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 水平居中
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
                    table.addCell(cell);
                } else {
                    strTableTitle = i + "--" + j;
                    tableTitle = new Paragraph(strTableTitle, conyentFont);
                    cell = new PdfPCell(tableTitle);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 水平居中
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
                    table.addCell(cell);
                }
            }
        }
        table.setSpacingAfter(15f);// 下留白
        doc.add(table);

        // 所有的都要指定中文，不然显示不出来
        // Phrase没有位置的操作,但是空格会被保留
        // 也可以使用表格处理，隐藏边框即可
        Phrase tPhrase = new Phrase("    申请人:                                     " + "                                         " + "                                         " + "保全试算金额:",
                conyentFont);
        Paragraph tParagraph = new Paragraph(tPhrase);
        doc.add(tParagraph);

        // 尾部表格处理
        PdfPTable footTable = new PdfPTable(2);
        footTable.setTotalWidth(760f);
        footTable.setWidths(new float[]{4.6f, 1f});
        footTable.setHorizontalAlignment(Element.ALIGN_LEFT);

        strTableTitle = "   申请日期：";
        tableTitle = new Paragraph(strTableTitle, conyentFont);
        cell = new PdfPCell(tableTitle);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
        cell.setBorderWidth(0);
        footTable.addCell(cell);

        strTableTitle = "受理人：";
        tableTitle = new Paragraph(strTableTitle, conyentFont);
        cell = new PdfPCell(tableTitle);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);// 水平居中
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
        cell.setBorderWidth(0);
        footTable.addCell(cell);

        strTableTitle = "   申请人电话：";
        tableTitle = new Paragraph(strTableTitle, conyentFont);
        cell = new PdfPCell(tableTitle);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);//
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
        cell.setBorderWidth(0);
        footTable.addCell(cell);

        strTableTitle = "受理日期：";
        tableTitle = new Paragraph(strTableTitle, conyentFont);
        cell = new PdfPCell(tableTitle);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);//
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);// 垂直居中
        cell.setBorderWidth(0);
        footTable.addCell(cell);

        doc.add(footTable);

        tParagraph = new Paragraph();
        Chunk tChunk = new Chunk("   说明:  ", conyentFont);
        tParagraph.add(tChunk);

        tChunk = new Chunk("                                                                               ", conyentFont);
        tChunk.setUnderline(0.1f, -2f);
        tParagraph.add(tChunk);
        tChunk = new Chunk("                                                                           ", conyentFont);
        tChunk.setUnderline(0.1f, -2f);
        tParagraph.add(tChunk);
        tChunk = new Chunk("                                                                        ", conyentFont);
        tChunk.setUnderline(0.1f, -2f);
        tParagraph.add(tChunk);

        tParagraph.setLeading(30f);
        tChunk = new Chunk("                                                                          ", conyentFont);
        tChunk.setUnderline(0.1f, -2f);
        tParagraph.add(tChunk);
        tChunk = new Chunk("                                                                          ", conyentFont);
        tChunk.setUnderline(0.1f, -2f);
        tParagraph.add(tChunk);

        doc.add(tParagraph);

        doc.close();
        System.out.println("结束.....");
    }

    public static void temp001() throws Exception {
        Document doc = new Document();

        String file = "temp_" + System.currentTimeMillis() + ".pdf";
        FileOutputStream out = new FileOutputStream(file);
        PdfWriter.getInstance(doc, out);
        doc.open();

        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Font titleFont = new Font(bfChinese, 12, Font.BOLD);
        Font headerFont = new Font(bfChinese, 11, Font.NORMAL);
        Font tableFont = new Font(bfChinese, 12, Font.NORMAL);

        Paragraph line001 = new Paragraph("附件2：资料服务室请假报批单", titleFont);
        line001.setAlignment(Rectangle.ALIGN_LEFT);
        doc.add(line001);

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        StringBuffer buffer = new StringBuffer();
        buffer.append("编号：")
                .append("test001")
                .append("                                                                                                                ")
                .append("填表时间：   ")
                .append(year)
                .append("   年   ")
                .append(month)
                .append("   月   ")
                .append(dayOfMonth)
                .append("   日   ");
        Paragraph line002 = new Paragraph(buffer.toString(), headerFont);
        line002.setSpacingBefore(10f);
        line002.setAlignment(Rectangle.ALIGN_LEFT);
        line002.setIndentationLeft(15);
//        line002.setFirstLineIndent(12f);
        doc.add(line002);

        PdfPTable table = new PdfPTable(4);
        table.setSpacingBefore(10f);
        table.setTotalWidth(500f);
        table.setWidths(new float[]{1, 2, 1, 2});
        table.setWidthPercentage(100);
        table.setLockedWidth(true);


        PdfPCell cell = new PdfPCell(new Paragraph("姓名", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED_ALL);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(50f);
//        cell.setRowspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("单位", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("时间", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(50f);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("出差地区\n" +
                "和单位", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(100f);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("同行人员\n" +
                "姓名职务", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(100f);
        System.out.println(cell.getBorder());
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("相关业务\n" +
                "替补人员", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(100f);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("出差任务\n或\n" +
                "休假理由", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(150f);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setColspan(3);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("本单位负\n" +
                "责人签字", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(100f);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(year + "   年   " + month + "   月   " + dayOfMonth + "   日   ", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("台室处\n" +
                "领导审批", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setFixedHeight(100f);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph(year + "   年   " + month + "   月   " + dayOfMonth + "   日   ", tableFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(cell);

        doc.add(table);

        Paragraph line003 = new Paragraph("注：请假、休假 1 天及以上时需填此表，并根据《资料服务室请假与考勤实施细则》的规定履行审批手续。", headerFont);
        line003.setAlignment(Rectangle.ALIGN_LEFT);
        doc.add(line003);

        doc.close();
        System.out.println("pdf生成结束............");

        addWaterMark(file, "mark" + File.separator + file, "国家气象信息中心", 150, 175);
        System.out.println("添加水印结束.............");
    }

    public static void addWaterMark(String srcFile, String destFile, String text, int textWidth, int textHeight) throws IOException, DocumentException {

        PdfReader pdfReader = new PdfReader(srcFile);
        PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(destFile));
        int total = pdfReader.getNumberOfPages();

        BaseFont baseFont = BaseFont.createFont("C:\\Windows\\Fonts\\STCAIYUN.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        PdfGState gs = new PdfGState();
        gs.setFillOpacity(0.5f);


        PdfContentByte content;
        Rectangle rectangle;
        for (int i = 1; i <= total; i++) {
            rectangle = pdfReader.getPageSizeWithRotation(i);
            content = pdfStamper.getOverContent(i);

            content.setGState(gs);
            content.beginText();
            content.setColorFill(BaseColor.GRAY);
            content.setFontAndSize(baseFont, 30);
            content.setTextMatrix(0, 0);

            float x = rectangle.getWidth();
            float y = rectangle.getHeight();
            System.out.println("x:  " + x + ";   y: " + y);

            for (int i1 = 0; i1 < 3; i1++) {
                for (int i2 = 0; i2 < 3; i2++) {
                    content.showTextAligned(Element.ALIGN_TOP, text, i1 * textWidth, i2 * textHeight, 30); //rotation:倾斜角度
                }
            }
            content.endText();
        }
        pdfStamper.close();

    }

    public static void waterMark(String inputFile, String outputFile, String waterMarkName) {
        try {
            PdfReader reader = new PdfReader(inputFile);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFile));

            BaseFont base = BaseFont.createFont("C:\\Windows\\Fonts\\STCAIYUN.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

            PdfGState gs = new PdfGState();
            gs.setFillOpacity(0.5f);

            JLabel label = new JLabel();
            FontMetrics metrics;
            int textH;
            int textW;
            label.setText(waterMarkName);
            java.awt.Font font = label.getFont();

            metrics = label.getFontMetrics(font);
            textH = metrics.getHeight();
            textW = metrics.stringWidth(label.getText());

            int total = reader.getNumberOfPages();
            PdfContentByte under;
            Rectangle pageRect;
            for (int i = 1; i <= total; i++) {
                pageRect = reader.getPageSizeWithRotation(i);
                under = stamper.getOverContent(i);
                under.saveState();
                under.setGState(gs);
                under.beginText();
                under.setColorFill(BaseColor.DARK_GRAY);
                under.setFontAndSize(base, 28);

                // 水印文字成30度角倾斜
                //你可以随心所欲的改你自己想要的角度
                for (int height = textH; height < pageRect.getHeight(); height = height + textH * 15) {
                    for (int width = textW; width < pageRect.getWidth() + textW; width = width + textW * 2) {
                        under.showTextAligned(Element.ALIGN_LEFT, waterMarkName, width - textW, height - textH, 30);
                    }
                }
                // 添加水印文字
                under.endText();
            }
            stamper.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
