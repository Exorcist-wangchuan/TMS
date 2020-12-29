package po;

public class ProcessRecord {
    private String eID;//流程ID
    private String dname;//流程名即流程类型
    private int finish;//流程进度
    private int apply_UID;//申请人id
    private String apply_Date;//申请时间
    private int first_Check_UID;//初审人id
    private String first_Check_Date;//初审日期
    private int final_Check_UID;//终审人id
    private String final_Check_Date;//终审日期

    //无参构造函数，用于自定义内容
    public ProcessRecord() {
    }

    public String geteID() {
        return eID;
    }

    public void seteID(String eID) {
        this.eID = eID;
    }

    public String getApply_Date() {
        return apply_Date;
    }

    public void setApply_Date(String apply_Date) {
        this.apply_Date = apply_Date;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public int getApply_UID() {
        return apply_UID;
    }

    public void setApply_UID(int apply_UID) {
        this.apply_UID = apply_UID;
    }

    public int getFirst_Check_UID() {
        return first_Check_UID;
    }

    public void setFirst_Check_UID(int first_Check_UID) {
        this.first_Check_UID = first_Check_UID;
    }

    public String getFirst_Check_Date() {
        return first_Check_Date;
    }

    public void setFirst_Check_Date(String first_Check_Date) {
        this.first_Check_Date = first_Check_Date;
    }

    public int getFinal_Check_UID() {
        return final_Check_UID;
    }

    public void setFinal_Check_UID(int final_Check_UID) {
        this.final_Check_UID = final_Check_UID;
    }

    public String getFinal_Check_Date() {
        return final_Check_Date;
    }

    public void setFinal_Check_Date(String final_Check_Date) {
        this.final_Check_Date = final_Check_Date;
    }
}
