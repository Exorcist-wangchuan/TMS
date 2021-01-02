package po;

import java.io.Serializable;

public class ToolEntityPrimaryKey implements Serializable{
    private static final long serialVersionUID = -1190986010439330142L;

    /*复合主键值*/
    private String code;
    private int seqID;

    public ToolEntityPrimaryKey()
    {

    }

    /*复合主键值的get和set方法*/

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSeqID() {
        return seqID;
    }

    public void setSeqID(int seqID) {
        this.seqID = seqID;
    }

    @Override
    public String toString() {
        return "code: " + this.code + "; seqID: " + this.seqID;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ToolEntityPrimaryKey other = (ToolEntityPrimaryKey) obj;
        if (this.code.equals(other.code)&&this.seqID==other.seqID)
        {
            return true;
        }
        return false;
    }
}
