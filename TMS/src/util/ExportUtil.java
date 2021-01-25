package util;

import dao.IPurchaseRecordDAO;
import org.apache.poi.hssf.usermodel.*;
import org.apache.struts2.ServletActionContext;
import po.PurchaseRecord;
import service.IPurchaseRecordService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ExportUtil {
    private IPurchaseRecordService purchaseRecordService;

    public void setPurchaseRecordService(IPurchaseRecordService purchaseRecordService) {
        this.purchaseRecordService = purchaseRecordService;
    }

    public String importExcel(){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("审批列表");

        //第一行
        HSSFRow row1 = sheet.createRow(0);
        //单元格
        row1.createCell(0).setCellValue("申请人ID");
        row1.createCell(1).setCellValue("Code");
        row1.createCell(2).setCellValue("SeqID");
        row1.createCell(3).setCellValue("单据号");
        row1.createCell(4).setCellValue("申请时间");
        //获取数据
        List<PurchaseRecord> list = purchaseRecordService.getPurchaseRecord();
        //生成内容
        for (int i=0;i<list.size();i++){
            HSSFRow row = sheet.createRow(i+1);
            row.createCell(0).setCellValue(list.get(i).getApplyUID());
            row.createCell(1).setCellValue(list.get(i).getCode_seqid().getCode());
            row.createCell(2).setCellValue(list.get(i).getCode_seqid().getSeqID());
            row.createCell(3).setCellValue(list.get(i).getBillNo());
            row.createCell(4).setCellValue(list.get(i).getPurchaseDate());
        }
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            ServletOutputStream output = response.getOutputStream();
            response.reset();
            String filename = "下载";
            response.setContentType("application/vnd.ms-excel;charset-utf-8");
            response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes(), StandardCharsets.ISO_8859_1)+".xls");
            workbook.write(output);
            output.flush();
            output.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }

}
