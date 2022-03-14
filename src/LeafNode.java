import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LeafNode extends Nodes {


    //private Map<Data, Integer> records = new LinkedHashMap<>(2);
    private ArrayList<Data> records  = new ArrayList<>();

    LeafNode(Data dataValue, int toPrint) {
        records.add(dataValue);
        this.toPrint = toPrint;

    }

    LeafNode(int toPrint) {
        this.toPrint = toPrint;
    }

    LeafNode(LeafNode node) {
        this.records = new ArrayList<>(node.getRecords());
        this.toPrint = node.toPrint;

    }

    void add(Data dataValue) {
        records.add(dataValue);
    }

    public ArrayList<Data> getRecords() {
        return records;

    }

    @Override
    public String toString() {

        ArrayList<Data> recordsToDisplay = new ArrayList<>();
        ArrayList<Data> forDuplicate = new ArrayList<>(KDTree.deletedRecords);
        if(!forDuplicate.isEmpty()) {
//            for (Data dataDuplicate : forDuplicate) {
//                if(this.records.contains(dataDuplicate)) {
//                    int occu=0;
//                    for(Data data: this.records) {
//                        if(data.equals(dataDuplicate)) {
//                            occu++;
//                            if(occu != 1) {
//                                recordsToDisplay.add(data);
//                            }
//                        } else {
//                            recordsToDisplay.add(data);
//                        }
//                    }
//                } else {
//                    recordsToDisplay.addAll(this.records);
//                }
////                for (Map.Entry<Age, Integer> recordToDisplayAgeIntegerEntry : deletedRecords.entrySet()) {
////                    if(recordToDisplayAgeIntegerEntry.getKey().getAge() != data.getKey().getAge()) {
////                    } else {
////                        System.out.println(data.getKey()+" "+recordToDisplayAgeIntegerEntry.getKey());
////                        System.out.println(data.getKey().hashCode() + " "+ recordToDisplayAgeIntegerEntry.getKey().hashCode());
////                        if(recordToDisplayAgeIntegerEntry.getValue() != data.getValue()) {
////                            recordsToDisplay.put(data.getKey(), data.getValue());
////                        }
////                    }
////
////                    //10,60
////                    //30,60
////                    //30,70
////
////                    //30,60
//////                if (recordToDisplayAgeIntegerEntry.getKey().getAge() != data.getKey().getAge() && data.getValue() != recordToDisplayAgeIntegerEntry.getValue())
//////                    //recordsToDisplay.remove(recordToDisplayAgeIntegerEntry.getKey());
//////                    recordsToDisplay.put(data.getKey(), data.getValue());
////                }
//            }


                for (Data record : this.records) {
                    if(!forDuplicate.contains(record))
                    {
                        recordsToDisplay.add(record);
                    }
                }

        } else {
            recordsToDisplay = new ArrayList<Data>(this.records);
        }

        return "\n" + getSpacesToDisplay(0) + "LeafNode{" +
                "\n" + getSpacesToDisplay(1) + "Records:" + recordsToDisplay +
                "\n" + getSpacesToDisplay(0) + "}";


    }

    public int getToPrint() {
        return toPrint;
    }
}
