import java.util.LinkedHashMap;
import java.util.Map;

public class LeafNode extends Nodes {

    //Ashwin
//    private Record record = new Record();

    private Map<Age, Integer> records = new LinkedHashMap<>(2);

    LeafNode(int ageValue, int salaryValue, int toPrint) {
        records.put(new Age(ageValue), salaryValue);
        this.toPrint = toPrint;

        //Ashwin
//        this.record.records.put(new Age(ageValue),salaryValue);
    }

    LeafNode(int toPrint) {
        this.toPrint = toPrint;
    }

    LeafNode(LeafNode node) {
        this.records = new LinkedHashMap<>(node.getRecords());
        this.toPrint = node.toPrint;

        //Ashwin
//        this.record.records=new LinkedHashMap<>(node.getRecords());
    }

    void add(int ageValue, int salaryValue) {
        records.put(new Age(ageValue), salaryValue);
        //Ashwin
//        this.record.records.put(new Age(ageValue),salaryValue);
    }

    public Map<Age, Integer> getRecords() {
        //Ashwin
//        return this.record.getRecords();
        return records;

    }

    @Override
    public String toString() {
//        return "\n"+getSpacesToDisplay(0)+"LeafNode{" +
//                "\n"+getSpacesToDisplay(1)+"Records:" + records+
//                "\n"+getSpacesToDisplay(0)+"}";

        //Ashwin
        return "\n" + getSpacesToDisplay(0) + "LeafNode{" +
                "\n" + getSpacesToDisplay(1) + "Records:" + records +
                "\n" + getSpacesToDisplay(0) + "}";
    }

    public int getToPrint() {
        return toPrint;
    }
}
