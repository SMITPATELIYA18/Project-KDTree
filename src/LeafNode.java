import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LeafNode extends Nodes {


    private Map<Age, Integer> records = new LinkedHashMap<>(2);

    LeafNode(Age ageValue, int salaryValue, int toPrint) {
        records.put(ageValue, salaryValue);
        this.toPrint = toPrint;

    }

    LeafNode(int toPrint) {
        this.toPrint = toPrint;
    }

    LeafNode(LeafNode node) {
        this.records = new LinkedHashMap<>(node.getRecords());
        this.toPrint = node.toPrint;

    }

    void add(Age ageValue, int salaryValue) {
        records.put(ageValue, salaryValue);
    }

    public Map<Age, Integer> getRecords() {
        return records;

    }

    @Override
    public String toString() {

        Map<Age, Integer> recordsToDisplay = new HashMap<>();
        Map<Age, Integer> deletedRecords = KDTree.deletedRecords;
        if(!deletedRecords.isEmpty()) {
            for (Map.Entry<Age, Integer> deletedAgeIntegerEntry : this.records.entrySet()) {
                if(!deletedRecords.containsKey(deletedAgeIntegerEntry.getKey())) {
                    recordsToDisplay.put(deletedAgeIntegerEntry.getKey(), deletedAgeIntegerEntry.getValue());
                }
//                for (Map.Entry<Age, Integer> recordToDisplayAgeIntegerEntry : deletedRecords.entrySet()) {
//                    if(recordToDisplayAgeIntegerEntry.getKey().getAge() != deletedAgeIntegerEntry.getKey().getAge()) {
//                    } else {
//                        System.out.println(deletedAgeIntegerEntry.getKey()+" "+recordToDisplayAgeIntegerEntry.getKey());
//                        System.out.println(deletedAgeIntegerEntry.getKey().hashCode() + " "+ recordToDisplayAgeIntegerEntry.getKey().hashCode());
//                        if(recordToDisplayAgeIntegerEntry.getValue() != deletedAgeIntegerEntry.getValue()) {
//                            recordsToDisplay.put(deletedAgeIntegerEntry.getKey(), deletedAgeIntegerEntry.getValue());
//                        }
//                    }
//
//                    //10,60
//                    //30,60
//                    //30,70
//
//                    //30,60
////                if (recordToDisplayAgeIntegerEntry.getKey().getAge() != deletedAgeIntegerEntry.getKey().getAge() && deletedAgeIntegerEntry.getValue() != recordToDisplayAgeIntegerEntry.getValue())
////                    //recordsToDisplay.remove(recordToDisplayAgeIntegerEntry.getKey());
////                    recordsToDisplay.put(deletedAgeIntegerEntry.getKey(), deletedAgeIntegerEntry.getValue());
//                }
            }
        } else {
            recordsToDisplay = new HashMap<>(this.records);
        }

        return "\n" + getSpacesToDisplay(0) + "LeafNode{" +
                "\n" + getSpacesToDisplay(1) + "Records:" + recordsToDisplay +
                "\n" + getSpacesToDisplay(0) + "}";


    }

    public int getToPrint() {
        return toPrint;
    }
}
