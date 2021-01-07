package po;

import java.io.Serializable;

public class Scrap implements Serializable {
    private ToolEntityPrimaryKey code_seqid;
    private int UID;
    private int lifecount;
    private String reason;
    private String eID;
    //表单中没有，自动填充
    //private String recOn;

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public ToolEntityPrimaryKey getCode_seqid() {
        return code_seqid;
    }

    public void setCode_seqid(ToolEntityPrimaryKey code_seqid) {
        this.code_seqid = code_seqid;
    }

    public int getLifecount() {
        return lifecount;
    }

    public void setLifecount(int lifecount) {
        this.lifecount = lifecount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String geteID() {
        return eID;
    }

    public void seteID(String eID) {
        this.eID = eID;
    }

    /*    public String getRecOn() {
        return recOn;
    }

    public void setRecOn(String recOn) {
        this.recOn = recOn;
    }*/
}
