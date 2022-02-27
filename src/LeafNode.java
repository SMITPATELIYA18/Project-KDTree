import java.util.LinkedHashMap;
import java.util.Map;

public class LeafNode extends Nodes {
    private Map<Age,Integer> records = new LinkedHashMap<>(2);

    LeafNode(int ageValue,int salaryValue,int toPrint) {
        records.put(new Age(ageValue),salaryValue);
        this.toPrint= toPrint;
    }

    LeafNode(int toPrint) {
        this.toPrint = toPrint;
    }

    LeafNode(LeafNode node) {
        this.records = new LinkedHashMap<>(node.getRecords());
        this.toPrint = node.toPrint;
    }

    void add(int ageValue, int salaryValue) {
        records.put(new Age(ageValue),salaryValue);
    }

    Map<Age,Integer> getRecords() {
        return records;
    }

    @Override
    public String toString() {
        return "\n"+getSpacesToDisplay(0)+"LeafNode{" +
                "\n"+getSpacesToDisplay(1)+"Records:" + records+
                "\n"+getSpacesToDisplay(0)+"}";
    }

    public int getToPrint() {
        return toPrint;
    }
}
