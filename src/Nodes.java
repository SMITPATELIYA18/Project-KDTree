public class Nodes {
    public int toPrint;

    String getSpacesToDisplay(int n) {
        String output = "";
        for (int i = 0; i < toPrint + n; i++) {
            output += "\t";
        }
        return output;
    }
}
