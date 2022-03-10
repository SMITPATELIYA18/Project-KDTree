import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner  =  new Scanner(System.in);
        KDTree kdTree = new KDTree();
        System.out.println("KD Tree Project");
        int userInput;
        do {
            System.out.println("1. Add Record");
            System.out.println("2. Delete Record");
            System.out.println("3. Print KD Tree");
            System.out.println("4. Exit");
            System.out.print("Enter you choice: ");
            userInput = Integer.parseInt(scanner.nextLine());
            if(userInput == 1) {
                int salary,age;
                System.out.print("Enter Salary: ");
                salary = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter Age: ");
                age = Integer.parseInt(scanner.nextLine());
                kdTree.insert(age,salary);
            } else if(userInput == 2) {
                int salary,age;
                System.out.print("Enter Salary to Delete: ");
                salary = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter Age to Delete: ");
                age = Integer.parseInt(scanner.nextLine());
                kdTree = deleteUsingAgeAndSalary(kdTree,age,salary);
            } else if(userInput == 3) {
                System.out.println("******DISPLAYING KD-TREE*********");
                displayKDTree(kdTree);
            }
        } while(userInput != 4);

//        //Insert
//        System.out.println("******BUILDING KD-TREE*********\n");
//        buildKDTree(kdTree);
//        displayKDTree(kdTree);
//
//        //Delete
//        int ageToBeDeleted = 25;
//        int salaryToBeDeleted = 60;
//        System.out.println("******DELETING age = " + ageToBeDeleted + " salary = " + salaryToBeDeleted + "*********");
//        kdTree = deleteUsingAgeAndSalary(kdTree, ageToBeDeleted, salaryToBeDeleted);
//
//        //Display
//        System.out.println("******DISPLAYING KD-TREE*********");
//        displayKDTree(kdTree);
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
//        kdTree = new KDTree();
//        KDTree.deletedRecords = deletedRecords;
//        buildKDTree(kdTree);
//        KDTree.deletedRecords = null;
        return kdTree;
    }

    public static void displayKDTree(KDTree kdTree) {
        System.out.println(kdTree);
    }
}
