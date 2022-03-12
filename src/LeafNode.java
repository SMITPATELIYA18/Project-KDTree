import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LeafNode extends Nodes {


    private Map<Age, Integer> records = new LinkedHashMap<>(2);

    LeafNode(int ageValue, int salaryValue, int toPrint) {
        records.put(new Age(ageValue), salaryValue);
        this.toPrint = toPrint;

    }

    LeafNode(int toPrint) {
        this.toPrint = toPrint;
    }

    LeafNode(LeafNode node) {
        this.records = new LinkedHashMap<>(node.getRecords());
        this.toPrint = node.toPrint;

    }

    void add(int ageValue, int salaryValue) {
        records.put(new Age(ageValue), salaryValue);
    }

    public Map<Age, Integer> getRecords() {
        return records;

    }

    @Override
    public String toString() {

        Map<Age, Integer> recordsToDisplay = this.records;
        Map<Integer, Integer> deletedRecords = KDTree.deletedRecords;

            for (Map.Entry<Integer, Integer> deletedAgeIntegerEntry : deletedRecords.entrySet()) {
                for (Map.Entry<Age, Integer> recordToDisplayAgeIntegerEntry : recordsToDisplay.entrySet()) {
                    if (recordToDisplayAgeIntegerEntry.getKey().getAge() == deletedAgeIntegerEntry.getKey())
                        recordsToDisplay.remove(recordToDisplayAgeIntegerEntry.getKey());
                }
            }

                return "\n" + getSpacesToDisplay(0) + "LeafNode{" +
                        "\n" + getSpacesToDisplay(1) + "Records:" + recordsToDisplay +
                        "\n" + getSpacesToDisplay(0) + "}";


    }

    public int getToPrint() {
        return toPrint;
    }
}
