//package com.boot.data.controller;
//
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//
//import java.io.IOException;
//
///**
// * @author 98548
// * @create 2018-12-20 16:53
// * @description
// */
//public class Temp {
//
//    private PdfPTable createPDF(Font font, List<WxdzType> wxdzlist, List<WxdzInf> list, String z_mernoshh,
//                                String z_daterq, int z_transcnt, double z_transamt,
//                                double z_transfee, int z_tkcnt, double z_tkamt, double z_tkfee,
//                                double z_remark) throws DocumentException,IOException {
//
//        PdfPTable table = new PdfPTable(10);// 创建一个多少列的表格
//
//        table.getDefaultCell().setMinimumHeight(50); // 设置默认的表格高度
//
//        int[] widths = new int[10];
//        for (int i = 0; i < 10; i++) widths[i] = 1;
//        widths[0] = 2;
////		widths[1] = 2;
////		widths[2] = 1;
////		widths[date.size()-1] =2;
//        table.setWidths(widths);// 设置所有宽度
//        table.setWidthPercentage(100f);
////		PdfPCell cell;
////		Phrase ph;
//        // cell = new PdfPCell(ph);
//        // cell.setColspan(date.size());//合并列
//        String[] tableName=new String[]{"商户名称","交易类型","交易日期","交易笔数","交易金额","手续费","退款笔数","退款金额","退款手续费","划账金额"};
//        for(int i=0;i<tableName.length;i++){
//
//            Paragraph element = new Paragraph(tableName[i], font);
//            PdfPCell cell = new PdfPCell(element);
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(cell);
//        }
//
//
//        for(int i=0;i<wxdzlist.size();i++){
//            List<WxdzInf> wxd = wxdzlist.get(i).getList();
//            for(int j=0;j<wxd.size();j++){
//
//                Paragraph element = new Paragraph(wxd.get(j).getMerName(),font);
//                PdfPCell cell1 = new PdfPCell(element);
//                cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//                table.addCell(cell1);
//
//                Paragraph element_1 = new Paragraph(wxd.get(j).getTranstype(),font);
//                PdfPCell cell_1 = new PdfPCell(element_1);
//                cell_1.setHorizontalAlignment(Element.ALIGN_LEFT);
//                table.addCell(cell_1);
//
//                Paragraph element_2 = new Paragraph(wxd.get(j).getDate(),font);
//                PdfPCell cell_2 = new PdfPCell(element_2);
//                cell_2.setHorizontalAlignment(Element.ALIGN_CENTER);
//                table.addCell(cell_2);
//
//                Paragraph element_3 = new Paragraph(wxd.get(j).getTranscnt()+"",font);//交易笔数
//                PdfPCell cell_3 = new PdfPCell(element_3);
//                cell_3.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                table.addCell(cell_3);
//
//                Paragraph element_4 = new Paragraph(ChangeNumPoint(String.valueOf(wxd.get(j).getTransamt())),font);
//                PdfPCell cell_4 = new PdfPCell(element_4);
//                cell_4.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                table.addCell(cell_4);
//
//                Paragraph element_5 = new Paragraph(ChangeNumPoint(String.valueOf(wxd.get(j).getTransfee())),font);
//                PdfPCell cell_5 = new PdfPCell(element_5);
//                cell_5.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                table.addCell(cell_5);
//
//                Paragraph element_6 = new Paragraph(wxd.get(j).getTkcnt()+"",font);//退款笔数
//                PdfPCell cell_6 = new PdfPCell(element_6);
//                cell_6.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                table.addCell(cell_6);
//
//                Paragraph element_7 = new Paragraph(ChangeNumPoint(String.valueOf(wxd.get(j).getTkamt())),font);
//                PdfPCell cell_7 = new PdfPCell(element_7);
//                cell_7.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                table.addCell(cell_7);
//
//                Paragraph element_8 = new Paragraph(ChangeNumPoint(String.valueOf(wxd.get(j).getTkfee())),font);
//                PdfPCell cell_8 = new PdfPCell(element_8);
//                cell_8.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                table.addCell(cell_8);
//
//                Paragraph element_9 = new Paragraph(ChangeNumPoint(String.valueOf(wxd.get(j).getRemark())),font);
//                PdfPCell cell_9 = new PdfPCell(element_9);
//                cell_9.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                table.addCell(cell_9);
//            }
//            //小计
//
//
//            Paragraph element_x1 = new Paragraph(wxdzlist.get(i).getMerno(),font);
//            PdfPCell cell_x1 = new PdfPCell(element_x1);
//            cell_x1.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell_x1.setColspan(2);//合并2列
//            table.addCell(cell_x1);
//
//            Paragraph element_x2 = new Paragraph(wxdzlist.get(i).getDate(),font);
//            PdfPCell cell_x2 = new PdfPCell(element_x2);
//            cell_x2.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(cell_x2);
//
//            Paragraph element_x3 = new Paragraph(wxdzlist.get(i).getTranscnt()+"",font);//交易笔数
//            PdfPCell cell_x3 = new PdfPCell(element_x3);
//            cell_x3.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            table.addCell(cell_x3);
//
//            Paragraph element_x4 = new Paragraph(ChangeNumPoint(String.valueOf(wxdzlist.get(i).getTransamt())),font);
//            PdfPCell cell_x4 = new PdfPCell(element_x4);
//            cell_x4.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            table.addCell(cell_x4);
//
//            Paragraph element_x5 = new Paragraph(ChangeNumPoint(String.valueOf(wxdzlist.get(i).getTransfee())),font);
//            PdfPCell cell_x5 = new PdfPCell(element_x5);
//            cell_x5.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            table.addCell(cell_x5);
//
//            Paragraph element_x6 = new Paragraph(wxdzlist.get(i).getTkcnt()+"",font);//退款笔数
//            PdfPCell cell_x6 = new PdfPCell(element_x6);
//            cell_x6.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            table.addCell(cell_x6);
//
//            Paragraph element_x7 = new Paragraph(ChangeNumPoint(String.valueOf(wxdzlist.get(i).getTkamt())),font);
//            PdfPCell cell_x7 = new PdfPCell(element_x7);
//            cell_x7.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            table.addCell(cell_x7);
//
//            Paragraph element_x8 = new Paragraph(ChangeNumPoint(String.valueOf(wxdzlist.get(i).getTkfee())),font);
//            PdfPCell cell_x8 = new PdfPCell(element_x8);
//            cell_x8.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            table.addCell(cell_x8);
//
//            Paragraph element_x9 = new Paragraph(ChangeNumPoint(String.valueOf(wxdzlist.get(i).getRemark())),font);
//            PdfPCell cell_x9 = new PdfPCell(element_x9);
//            cell_x9.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            table.addCell(cell_x9);//----------------------------------------------------------------------------------------------------------------------------------------------------------
//
//        }
//        //合计
//
//		/*String z_mernoshh= "合计";//合计
//		String z_daterq="";// 交易日期
//		int z_transcnt=0;// 交易笔数
//		double z_transamt=0;// 交易金额
//		double =0;// 手续费
//		int =0;// 退款笔数
//		double =0;// 退款金额
//		double =0;// 退款手续费
//		double =0;// 划账金额
//*/		 Paragraph element_x1 = new Paragraph(z_mernoshh,font);
//        PdfPCell cell_x1 = new PdfPCell(element_x1);
//        cell_x1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell_x1.setColspan(2);//合并2列
//        table.addCell(cell_x1);
//
//        Paragraph element_x2 = new Paragraph(z_daterq,font);
//        PdfPCell cell_x2 = new PdfPCell(element_x2);
//        cell_x2.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(cell_x2);
//
//        Paragraph element_x3 = new Paragraph(z_transcnt+"",font);//交易笔数
//        PdfPCell cell_x3 = new PdfPCell(element_x3);
//        cell_x3.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        table.addCell(cell_x3);
//
//        Paragraph element_x4 = new Paragraph(ChangeNumPoint(String.valueOf(z_transamt)),font);
//        PdfPCell cell_x4 = new PdfPCell(element_x4);
//        cell_x4.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        table.addCell(cell_x4);
//
//        Paragraph element_x5 = new Paragraph(ChangeNumPoint(String.valueOf(z_transfee)),font);
//        PdfPCell cell_x5 = new PdfPCell(element_x5);
//        cell_x5.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        table.addCell(cell_x5);
//
//        Paragraph element_x6 = new Paragraph(z_tkcnt+"",font);//退款笔数
//        PdfPCell cell_x6 = new PdfPCell(element_x6);
//        cell_x6.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        table.addCell(cell_x6);
//
//        Paragraph element_x7 = new Paragraph(ChangeNumPoint(String.valueOf(z_tkamt)),font);
//        PdfPCell cell_x7 = new PdfPCell(element_x7);
//        cell_x7.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        table.addCell(cell_x7);
//
//        Paragraph element_x8 = new Paragraph(ChangeNumPoint(String.valueOf(z_tkfee)),font);
//        PdfPCell cell_x8 = new PdfPCell(element_x8);
//        cell_x8.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        table.addCell(cell_x8);
//
//        Paragraph element_x9 = new Paragraph(ChangeNumPoint(String.valueOf(z_remark)),font);
//        PdfPCell cell_x9 = new PdfPCell(element_x9);
//        cell_x9.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        table.addCell(cell_x9);
//        return table;
//    }
//
//}
