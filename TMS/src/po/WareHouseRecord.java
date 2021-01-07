package po;

import java.io.Serializable;
import java.util.Date;

public class WareHouseRecord implements Serializable {
    private ToolEntityPrimaryKey code_seqid;
    private Date RegDate;
    private int Manager;
    private int Operator;
    private boolean YN;
    private int PID;
    private int Name;
    private String Location;
    private String Model;
    private String PartNo;
    private String Family;

    public ToolEntityPrimaryKey getCode_seqid() {
        return code_seqid;
    }

    public void setCode_seqid(ToolEntityPrimaryKey code_seqid) {
        this.code_seqid = code_seqid;
    }

    public Date getRegDate() {
        return RegDate;
    }

    public void setRegDate(Date regDate) {
        RegDate = regDate;
    }

    public int getManager() {
        return Manager;
    }

    public void setManager(int manager) {
        Manager = manager;
    }

    public int getOperator() {
        return Operator;
    }

    public void setOperator(int operator) {
        Operator = operator;
    }

    public boolean isYN() {
        return YN;
    }

    public void setYN(boolean YN) {
        this.YN = YN;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getName() {
        return Name;
    }

    public void setName(int name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getPartNo() {
        return PartNo;
    }

    public void setPartNo(String partNo) {
        PartNo = partNo;
    }

    public String getFamily() {
        return Family;
    }

    public void setFamily(String family) {
        Family = family;
    }
}
