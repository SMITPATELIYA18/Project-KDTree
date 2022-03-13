import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class KDTree {

    public static ArrayList<Data> deletedRecords = new ArrayList<>();
    private Nodes rootNode;

    public void insert(int ageValue, int salaryValue) {
//        if (deletedRecords != null && deletedRecords.containsKey(ageValue) && deletedRecords.get(ageValue) == salaryValue)
//            return;
        Data tempData = new Data(ageValue,salaryValue);
        Main.allValues.add(tempData);
        if (rootNode == null) {
            Node temp = new Node(Constants.Salary, salaryValue, 1);
            LeafNode tempLeafNode = new LeafNode(tempData, 3);
            temp.setRight(tempLeafNode);
            rootNode = temp;
            //Main.values.putAll(tempLeafNode.getRecords());
            return;
        }
        insertNodes(rootNode, tempData);
    }

    private void insertNodes(Nodes node, Data dataValue) {
        Node tempNode = (Node) node;
        int compareValue = tempNode.getName() == Constants.Age ? dataValue.getAge() : dataValue.getSalary();
        Constants nextConstants = tempNode.getName() == Constants.Age ? Constants.Salary : Constants.Age;
        if (tempNode.getValue() <= compareValue) {
            if (tempNode.right.getClass() == LeafNode.class) {
                tempNode.right = updateLeafNode((LeafNode) tempNode.right, dataValue,
                        nextConstants);
                return;
            }
            insertNodes(tempNode.right, dataValue);
        } else {
            if (tempNode.left.getClass() == LeafNode.class) {
                tempNode.left = updateLeafNode((LeafNode) tempNode.left, dataValue,
                        nextConstants);
                return;
            }
            insertNodes(tempNode.left, dataValue);
        }
    }

    private Nodes updateLeafNode(LeafNode node, Data dataValue, Constants constants) {
        ArrayList<Data> records = node.getRecords();
        if (records.size() < 2) {
            LeafNode newNode = new LeafNode(node);
            newNode.add(dataValue);
            //Main.values.putAll(newNode.getRecords());
            return newNode;
        }
        records.add(dataValue);

        //Main.values.putAll(records);
//        Comparator<Map.Entry<Age,Integer>> comparator = constants == Constants.Age ? Map.Entry.comparingByKey(new
//        Age()) :
//                Map.Entry.comparingByValue();
        double average = constants == Constants.Age ?
                records.stream().collect(Collectors.averagingInt(Data::getAge)) :
                records.stream().collect(Collectors.averagingInt(Data::getSalary));
//        Map<Age, Integer> sortedMap =
//                records.entrySet().stream().sorted(comparator).collect(Collectors.toMap(Map.Entry::getKey,
//                        Map.Entry::getValue,
//                        (e1,
//                         e2) -> e1, LinkedHashMap::new));
        //Map.Entry<Age, Integer> medianValue = sortedMap.entrySet().stream().skip(1).findFirst().get();
        //int medianAnswer = constants ==Constants.Age ? medianValue.getKey().getAge() : medianValue.getValue();
        int medianAnswer = (int) average;
        Node newNode = new Node(constants, medianAnswer, node.getToPrint() + 1);
        LeafNode newLeafNodeLeft = new LeafNode(node.getToPrint() + 3);
        LeafNode newLeafNodeRight = new LeafNode(node.getToPrint() + 3);
        for (Data entry : records) {
            int entryAnswer = constants == Constants.Age ? entry.getAge() : entry.getSalary();
            if (entryAnswer < medianAnswer) {
                newLeafNodeLeft.add(entry);
            } else {
                newLeafNodeRight.add(entry);
            }
        }
        if(newLeafNodeLeft.getRecords().size() > 2 || newLeafNodeRight.getRecords().size()>2) {
            average = (int) average + new Random().nextInt(10);
            newNode = new Node(constants, medianAnswer, node.getToPrint() + 1);
            newLeafNodeLeft = new LeafNode(node.getToPrint() + 3);
            newLeafNodeRight = new LeafNode(node.getToPrint() + 3);
            for (Data entry : records) {
                int entryAnswer = constants == Constants.Age ? entry.getAge() : entry.getSalary();
                if (entryAnswer < medianAnswer) {
                    newLeafNodeLeft.add(entry);
                } else {
                    newLeafNodeRight.add(entry);
                }
            }
        }
        newNode.left = newLeafNodeLeft;
        newNode.right = newLeafNodeRight;
        return newNode;
    }


    public Data delete(int ageValue, int salaryValue) {
//        deletedRecords = new HashMap<>();
        if (deleteValue(rootNode, ageValue, salaryValue)) {
            return new Data(ageValue,salaryValue);
        } else {
            System.out.println("NOT FOUND\n");
            return null;
        }
//        if (deletedRecords == null || deletedRecords.size() == 0)
//            System.out.println("NOT FOUND\n");
//        return deletedRecords;
    }

    private boolean deleteValue(Nodes node, int ageValue, int salaryValue) {

        if (rootNode == null) {
            return false;
        }

        Node tempNode = (Node) node;
        int compareValue = tempNode.getName() == Constants.Age ? ageValue : salaryValue;
        if (tempNode.getValue() <= compareValue) {
            if (tempNode.right.getClass() == LeafNode.class) {
                LeafNode leafNodeToCheck = new LeafNode((LeafNode) tempNode.right);
                return checkValueForDeletion(leafNodeToCheck, ageValue, salaryValue);
            }
            return deleteValue(tempNode.right, ageValue, salaryValue);
        } else {
            if (tempNode.left.getClass() == LeafNode.class) {
                LeafNode leafNodeToCheck = new LeafNode((LeafNode) tempNode.left);
                return checkValueForDeletion(leafNodeToCheck, ageValue, salaryValue);
            }
            return deleteValue(tempNode.left, ageValue, salaryValue);
        }

//        return false;
    }

    public boolean checkValueForDeletion(LeafNode node, int ageValue, int salaryValue) {
        //Ashwin
        //Map<Age, Integer> records = node.getRecords();
        for (Data ageIntegerEntry : node.getRecords()) {
            if (ageIntegerEntry.getAge() == ageValue && ageIntegerEntry.getSalary() == salaryValue) {
                System.out.println("Found and deleted\n");
                deletedRecords.add(ageIntegerEntry);
                return true;
            }
        }
        return false;
    }

    public Nodes getRootNode() {
        return rootNode;
    }

    @Override
    public String toString() {
        return "KDTree(" +
                rootNode.getSpacesToDisplay(0) + rootNode +
                rootNode.getSpacesToDisplay(0) + "\n)";
    }
}
