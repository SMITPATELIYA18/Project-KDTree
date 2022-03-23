import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Tree class with insert, delete operations
 */
public class KDTree {

    public static ArrayList<Data> deletedRecords = new ArrayList<>();
    private Nodes rootNode;

    public void insert(int ageValue, int salaryValue) {
        Data tempData = new Data(ageValue,salaryValue);

        if (!deletedRecords.isEmpty())
        {
            Iterator<Data> iterator = deletedRecords.iterator();
            while (iterator.hasNext()) {
                Data data = iterator.next();
                if (data.getAge()==ageValue && data.getSalary()==salaryValue) {
                    iterator.remove();
                    return;
                }
            }
        }
//        if(!Main.allValues.contains(tempData))
            Main.allValues.add(tempData);
        if (rootNode == null) {
            Node temp = new Node(Constants.Salary, salaryValue, 1);
            LeafNode tempLeafNode = new LeafNode(tempData, 3);
            temp.setRight(tempLeafNode);
            rootNode = temp;
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
            return newNode;
        }
        records.add(dataValue);

        double average = constants == Constants.Age ?
                records.stream().collect(Collectors.averagingInt(Data::getAge)) :
                records.stream().collect(Collectors.averagingInt(Data::getSalary));
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
        if (deleteValue(rootNode, ageValue, salaryValue)) {
            return new Data(ageValue,salaryValue);
        } else {
            System.out.println("NOT FOUND\n");
            return null;
        }

    }

    private boolean deleteValue(Nodes node, int ageValue, int salaryValue) {

        if (rootNode == null) {
            return false;
        }

        Node tempNode = (Node) node;
        int compareValue = tempNode.getName() == Constants.Age ? ageValue : salaryValue;
        if (tempNode.getValue() <= compareValue) {
            if (tempNode.right.getClass() == LeafNode.class) {
//                LeafNode leafNodeToCheck = new LeafNode((LeafNode) tempNode.right);
                return checkValueForDeletion((LeafNode) tempNode.right, ageValue, salaryValue);
            }
            return deleteValue(tempNode.right, ageValue, salaryValue);
        } else {
            if (tempNode.left.getClass() == LeafNode.class) {
//                LeafNode leafNodeToCheck = new LeafNode((LeafNode) tempNode.left);
                return checkValueForDeletion((LeafNode) tempNode.left, ageValue, salaryValue);
            }
            return deleteValue(tempNode.left, ageValue, salaryValue);
        }
    }

    public boolean checkValueForDeletion(LeafNode node, int ageValue, int salaryValue) {
        boolean deleted = false;
        for (Data ageIntegerEntry : node.getRecords()) {
            if (ageIntegerEntry.getAge() == ageValue && ageIntegerEntry.getSalary() == salaryValue) {
                System.out.println("Found and deleted\n");
                deletedRecords.add(ageIntegerEntry);
                deleted = true;
                break;
            }
        }
        if(deleted) {
            Iterator<Data> records = node.getRecords().iterator();
            while (records.hasNext()) {
                Data deletedData = records.next();
                if (deletedData.equals(new Data(ageValue,salaryValue))) {
                    node.getRecords().remove(deletedData);
//                    records.remove();
//                    System.out.println("Deleted");
                    break;
                }
            }
            return true;
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
