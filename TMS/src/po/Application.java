package po;

import java.util.Date;
import java.util.List;

public class Application {
    private int id;
    private String code;
    private String name;
    private int familyID;
    private List<String> model;
    private List<String> partNo;
    private int upl;
    private String usedFor;
    private int period;
    private int owner;
    private int workcellID;
    //表单中没有，自动填充
    private String recOn;
    private int recBy;

    public String getRecOn() {
        return recOn;
    }

    public void setRecOn(String recOn) {
        this.recOn = recOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFamilyID() {
        return familyID;
    }

    public void setFamilyID(int familyID) {
        this.familyID = familyID;
    }

    public List<String> getModel() {
        return model;
    }

    public void setModel(List<String> model) {
        this.model = model;
    }

    public List<String> getPartNo() {
        return partNo;
    }

    public void setPartNo(List<String> partNo) {
        this.partNo = partNo;
    }

    public int getUpl() {
        return upl;
    }

    public void setUpl(int upl) {
        this.upl = upl;
    }

    public String getUsedFor() {
        return usedFor;
    }

    public void setUsedFor(String usedFor) {
        this.usedFor = usedFor;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getWorkcellID() {
        return workcellID;
    }

    public void setWorkcellID(int workcellID) {
        this.workcellID = workcellID;
    }

    public int getRecBy() {
        return recBy;
    }

    public void setRecBy(int recBy) {
        this.recBy = recBy;
    }
}
