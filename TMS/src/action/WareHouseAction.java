package action;

import com.opensymphony.xwork2.ActionContext;
import po.ToolEntity;
import po.WareHouseRecord;
import pojo.CheckList;
import service.IWareHouseService;
import service.ToolEntityService;
import service.WareHouseService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WareHouseAction {
    private WareHouseRecord wareHouseRecord;
    private IWareHouseService wareHouseService = null;
    private ToolEntityService toolEntityService=null;
    private CheckList checkList;

    public WareHouseRecord getWareHouseRecord() {
        return wareHouseRecord;
    }

    public void setWareHouseRecord(WareHouseRecord wareHouseRecord) {
        this.wareHouseRecord = wareHouseRecord;
    }

    public IWareHouseService getWareHouseService() {
        return wareHouseService;
    }

    public void setWareHouseService(IWareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }

    public ToolEntityService getToolEntityService() {
        return toolEntityService;
    }

    public void setToolEntityService(ToolEntityService toolEntityService) {
        this.toolEntityService = toolEntityService;
    }

    public CheckList getCheckList() {
        return checkList;
    }

    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }

    //高级员工
    public String dealWareHouse() {
        WareHouseRecord res = wareHouseService.searchWareHouse(wareHouseRecord);//查看是否存在
        if (res.getCode_seqid().getCode() != "nothing") {//存在
            Date old = res.getRegDate();
            Date nnew = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            //获得距离出库的天数+1，即当天取当天还也算一天
            int day = (int)((nnew.getTime() - old.getTime()) / (24 * 60 * 60 * 1000))+1;
            System.out.println("day"+day);
            boolean r = wareHouseService.INYN(wareHouseRecord);//改为入库状态

            //修改toolentity的usedcount
            String pk=wareHouseRecord.getCode_seqid().getCode().toString()+"&"+wareHouseRecord.getCode_seqid().getSeqID();
            ToolEntity rr=toolEntityService.getToolEntity(pk);
            if(rr!=null){
                day+=rr.getUsedCount();//将这次的使用周期和之前的相加
                rr.setUsedCount(day);
                boolean ress=toolEntityService.updateToolEntity(rr);
            }

            return "success";
        }

        //保存员工进库记录
        boolean firstRes = wareHouseService.saveWareHouse(wareHouseRecord);
        //判断
        if (firstRes) return "success";
        else return "fail";
    }

    //高级员工
    //高级员工获取入库记录
    public String getWareHouseRecords() {
        List<WareHouseRecord> list = new ArrayList<>();
        list = wareHouseService.getWareHouseRecord();
        ActionContext.getContext().getSession().put("WareHouseRecords", list);
        return "success";
    }

    //高级员工审核出库
    public String OutHouse() {
        List<String> passedList = checkList.getCheckList();
        /*if (passedList == null) System.out.println(111);
        else System.out.println(222);
        System.out.println(passedList.size());
        System.out.println(passedList.get(0));
        for (int i = 0; i < passedList.size(); i++) {
            System.out.println(passedList.get(i));
        }*/

        boolean res = wareHouseService.ChangeYN(passedList);
        if (res) return "success";
        else return "fail";
    }
}
