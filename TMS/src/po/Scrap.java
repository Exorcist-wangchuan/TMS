package po;

import java.io.Serializable;

public class Scrap implements Serializable {
    private int UID;
    private String Code;
    private int SeqID;
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

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public int getSeqID() {
        return SeqID;
    }

    public void setSeqID(int seqID) {
        SeqID = seqID;
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
