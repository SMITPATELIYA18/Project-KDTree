import java.util.Map;

public class Main {

    public static void main(String[] args) {

        //Insert
        KDTree kdTree = new KDTree();
        System.out.println("******BUILDING KD-TREE*********\n");
        buildKDTree(kdTree);
        displayKDTree(kdTree);

        //Delete
        int ageToBeDeleted = 25;
        int salaryToBeDeleted = 60;
        System.out.println("******DELETING age = " + ageToBeDeleted + " salary = " + salaryToBeDeleted + "*********");
        kdTree = deleteUsingAgeAndSalary(kdTree, ageToBeDeleted, salaryToBeDeleted);

        //Display
        System.out.println("******DISPLAYING KD-TREE*********");
        displayKDTree(kdTree);
    }

    public static void buildKDTree(KDTree kdTree) {
        int[] demoKey = {25, 45, 50, 50, 50, 70, 85, 30, 25, 45, 50, 60};
        int[] demoValue = {60, 60, 75, 100, 120, 110, 140, 260, 400, 350, 275, 260};
        for (int i = 0; i < demoKey.length; i++) {
            kdTree.insert(demoKey[i], demoValue[i]);
        }

    }

    public static KDTree deleteUsingAgeAndSalary(KDTree kdTree, int age, int salary) {
        Map<Integer, Integer> deletedRecords = kdTree.delete(age, salary);
        kdTree = new KDTree();
        KDTree.deletedRecords = deletedRecords;
        buildKDTree(kdTree);
        KDTree.deletedRecords = null;
        return kdTree;
    }

    public static void displayKDTree(KDTree kdTree) {
        System.out.println(kdTree);
    }
}
