import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class KDTree {

    public static Map<Integer, Integer> deletedRecords = new HashMap<>();
    private Nodes rootNode;

    public void insert(int ageValue, int salaryValue) {
        if (deletedRecords != null && deletedRecords.containsKey(ageValue) && deletedRecords.get(ageValue) == salaryValue)
            return;
        if (rootNode == null) {
            Node temp = new Node(Constants.Salary, salaryValue, 1);
            LeafNode tempLeafNode = new LeafNode(ageValue, salaryValue, 3);
            temp.setRight(tempLeafNode);
            rootNode = temp;
            return;
        }
        insertNodes(rootNode, ageValue, salaryValue);
    }

    private void insertNodes(Nodes node, int ageValue, int salaryValue) {
        Node tempNode = (Node) node;
        int compareValue = tempNode.getName() == Constants.Age ? ageValue : salaryValue;
        Constants nextConstants = tempNode.getName() == Constants.Age ? Constants.Salary : Constants.Age;
        if (tempNode.getValue() <= compareValue) {
            if (tempNode.right.getClass() == LeafNode.class) {
                tempNode.right = updateLeafNode((LeafNode) tempNode.right, ageValue, salaryValue,
                        nextConstants);
                return;
            }
            insertNodes(tempNode.right, ageValue, salaryValue);
        } else {
            if (tempNode.left.getClass() == LeafNode.class) {
                tempNode.left = updateLeafNode((LeafNode) tempNode.left, ageValue, salaryValue,
                        nextConstants);
                return;
            }
            insertNodes(tempNode.left, ageValue, salaryValue);
        }
    }

    private Nodes updateLeafNode(LeafNode node, int ageValue, int salaryValue, Constants constants) {
        Map<Age, Integer> records = node.getRecords();
        if (records.size() < 2) {
            LeafNode newNode = new LeafNode(node);
            newNode.add(ageValue, salaryValue);
            return newNode;
        }
        records.put(new Age(ageValue), salaryValue);
//        Comparator<Map.Entry<Age,Integer>> comparator = constants == Constants.Age ? Map.Entry.comparingByKey(new
//        Age()) :
//                Map.Entry.comparingByValue();
        double average = constants == Constants.Age ?
                records.entrySet().stream().collect(Collectors.averagingInt(entry -> entry.getKey().getAge())) :
                records.entrySet().stream().collect(Collectors.averagingInt(entry -> entry.getValue()));
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
        for (Map.Entry<Age, Integer> entry : records.entrySet()) {
            int entryAnswer = constants == Constants.Age ? entry.getKey().getAge() : entry.getValue();
            if (entryAnswer < medianAnswer) {
                newLeafNodeLeft.add(entry.getKey().getAge(), entry.getValue());
            } else {
                newLeafNodeRight.add(entry.getKey().getAge(), entry.getValue());
            }
        }
        newNode.left = newLeafNodeLeft;
        newNode.right = newLeafNodeRight;
        return newNode;
    }


    public Map<Integer, Integer> delete(int ageValue, int salaryValue) {
//        deletedRecords = new HashMap<>();
        deleteValue(rootNode, ageValue, salaryValue);
        if (deletedRecords == null || deletedRecords.size() == 0)
            System.out.println("NOT FOUND\n");
        return deletedRecords;
    }

    private void deleteValue(Nodes node, int ageValue, int salaryValue) {

        if (rootNode == null) {
            return;
        }

        Node tempNode = (Node) node;
        int compareValue = tempNode.getName() == Constants.Age ? ageValue : salaryValue;
        if (tempNode.getValue() <= compareValue) {
            if (tempNode.right.getClass() == LeafNode.class) {
                LeafNode leafNodeToCheck = new LeafNode((LeafNode) tempNode.right);
                checkValueForDeletion(leafNodeToCheck, ageValue, salaryValue);
                return;
            }
            deleteValue(tempNode.right, ageValue, salaryValue);
        } else {
            if (tempNode.left.getClass() == LeafNode.class) {
                LeafNode leafNodeToCheck = new LeafNode((LeafNode) tempNode.left);
                checkValueForDeletion(leafNodeToCheck, ageValue, salaryValue);
                return;
            }
            deleteValue(tempNode.left, ageValue, salaryValue);
        }

    }

    public void checkValueForDeletion(LeafNode node, int ageValue, int salaryValue) {
        //Ashwin
        Map<Age, Integer> records = node.getRecords();
        for (Map.Entry<Age, Integer> ageIntegerEntry : records.entrySet()) {
            if (ageIntegerEntry.getKey().getAge() == ageValue && ageIntegerEntry.getValue() == salaryValue) {
                System.out.println("Found and deleted\n");
                deletedRecords.put(ageValue, salaryValue);
                return;
            }
        }
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
