package po;

public class PurchaseRecord {
    private ToolEntityPrimaryKey code_seqid;
    private int applyUID;
    private String billNo;
    private String purchaseDate;
    private String img;
    private String eID;

    public ToolEntityPrimaryKey getCode_seqid() {
        return code_seqid;
    }

    public void setCode_seqid(ToolEntityPrimaryKey code_seqid) {
        this.code_seqid = code_seqid;
    }

    public String geteID() {
        return eID;
    }

    public void seteID(String eID) {
        this.eID = eID;
    }

    public int getApplyUID() {
        return applyUID;
    }

    public void setApplyUID(int applyUID) {
        this.applyUID = applyUID;
    }


    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
