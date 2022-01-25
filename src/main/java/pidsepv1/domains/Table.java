package pidsepv1.domains;

import com.opencsv.bean.CsvBindByName;

public class Table {
    @CsvBindByName(column = "Pid")
    private long pid;
    @CsvBindByName(column = "Layer")
    private String layer;

    public Table() {
    }

    public Table(long pid, String layer) {
        this.pid = pid;
        this.layer = layer;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    @Override
    public String toString() {
        return "Table{" +
                "pid=" + pid +
                ", layer='" + layer + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if(obj instanceof Table)
        {
            Table temp = (Table) obj;
            if(this.pid==(temp.pid) && this.layer.equals(temp.layer))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub

        return (this.layer.hashCode());
    }
}
