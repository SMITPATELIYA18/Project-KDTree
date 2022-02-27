import java.util.Comparator;
import java.util.Map;

public class MapSortByValue
        implements Comparator<Map.Entry<Integer, Integer>> {
    @Override
    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
        if (o1.getValue() < o2.getValue()) {
            return 1;
        } else if (o1.getValue() > o2.getValue()) {
            return -1;
        } else {
            return 0;
        }
    }
}
