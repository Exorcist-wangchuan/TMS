package po;

public class PeriodCheck {
    private ToolEntityPrimaryKey code_seqid;
    private String purchaseDate;
    private int pmPeriod;
    private String nextCheckDate;
    private int scrapTime;

    public ToolEntityPrimaryKey getCode_seqid() {
        return code_seqid;
    }

    public void setCode_seqid(ToolEntityPrimaryKey code_seqid) {
        this.code_seqid = code_seqid;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getPmPeriod() {
        return pmPeriod;
    }

    public void setPmPeriod(int pmPeriod) {
        this.pmPeriod = pmPeriod;
    }

    public String getNextCheckDate() {
        return nextCheckDate;
    }

    public void setNextCheckDate(String nextCheckDate) {
        this.nextCheckDate = nextCheckDate;
    }

    public int getScrapTime() {
        return scrapTime;
    }

    public void setScrapTime(int scrapTime) {
        this.scrapTime = scrapTime;
    }
}
