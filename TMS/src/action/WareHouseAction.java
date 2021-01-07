package action;

import com.opensymphony.xwork2.ActionContext;
import po.WareHouseRecord;
import pojo.CheckList;
import service.WareHouseService;

import java.util.ArrayList;
import java.util.List;

public class WareHouseAction {
    private WareHouseRecord wareHouseRecord;
    private WareHouseService wareHouseService=null;
    private CheckList checkList;

    public WareHouseRecord getWareHouseRecord() {
        return wareHouseRecord;
    }

    public void setWareHouseRecord(WareHouseRecord wareHouseRecord) {
        this.wareHouseRecord = wareHouseRecord;
    }

    public WareHouseService getWareHouseService() {
        return wareHouseService;
    }

    public void setWareHouseService(WareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }

    public CheckList getCheckList() {
        return checkList;
    }

    public void setCheckList(CheckList checkList) {
        this.checkList = checkList;
    }

    //高级员工
    public String dealWareHouse(){
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
    public String OutHouse(){
        List<String> passedList = checkList.getCheckList();
        if (passedList==null) System.out.println(111);
        else System.out.println(222);
        System.out.println(passedList.size());
        System.out.println(passedList.get(0));
        for (int i=0;i<passedList.size();i++){
            System.out.println(passedList.get(i));
        }
        boolean res = wareHouseService.ChangeYN(passedList);
        if (res) return "success";
        else return "fail";
    }
}
