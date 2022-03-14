/**
 * Node class which takes up a constant either Age or Salary
 */
public class Node extends Nodes {
    private final Constants name;
    private final int value;
    Nodes left;
    Nodes right;

    Node(Constants name, int value, int toPrint) {
        this.name = name;
        this.value = value;
        this.toPrint = toPrint;
        this.left = new LeafNode(toPrint + 2);
        this.right = new LeafNode(toPrint + 2);
    }

    Node(Node node) {
        this.name = node.name;
        this.value = node.value;
        this.toPrint = node.toPrint + 1;
        this.left = new Node((Node) node.left);
        this.right = new Node((Node) node.right);
    }

    public Constants getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public void setLeft(Nodes left) {
        this.left = left;
    }

    public void setRight(Nodes right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "\n" + getSpacesToDisplay(0) + "Node[" +
                "\n" + getSpacesToDisplay(1) + "Name=" + name +
                "\n" + getSpacesToDisplay(1) + "Value=" + value +
                "\n" + getSpacesToDisplay(1) + "Left<" + left +
                "\n" + getSpacesToDisplay(1) + ">\n" + getSpacesToDisplay(1) + "Right<" + right +
                "\n" + getSpacesToDisplay(1) + ">\n" + getSpacesToDisplay(0) + "]";
    }

    public int getToPrint() {
        return toPrint;
    }
}
