package po;

public class ToolEntity {
    private ToolEntityPrimaryKey code_seqid;
    private String billNo;
    private String regDate;
    private int usedCount;
    private String location;

    public ToolEntityPrimaryKey getCode_seqid() {
        return code_seqid;
    }

    public void setCode_seqid(ToolEntityPrimaryKey code_seqid) {
        this.code_seqid = code_seqid;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(int usedCount) {
        this.usedCount = usedCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
