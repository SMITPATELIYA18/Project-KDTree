import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Driver class that takes care of user interaction
 */
public class Main {
    static ArrayList<Data> allValues = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        KDTree kdTree = null;
        kdTree = new KDTree();
        System.out.println("KD Tree Project");
        int userInput;
        do {
            System.out.println("1. Build KD tree");
            System.out.println("2. Delete Record");
            System.out.println("3. Print KD Tree");
            System.out.println("4. Exit");
            System.out.print("Enter you choice: ");
            userInput = Integer.parseInt(scanner.nextLine());
            if (userInput == 1) {
                buildKDTree(kdTree);
            } else if (userInput == 2) {
                if (kdTree != null) {
                    int salary, age;
                    System.out.print("Enter Salary to Delete: ");
                    salary = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Age to Delete: ");
                    age = Integer.parseInt(scanner.nextLine());
                    kdTree = deleteUsingAgeAndSalary(kdTree, age, salary);
                } else
                    System.out.println("KDTree does not exist! Please build before deleting");
            } else if (userInput == 3) {
                if (kdTree != null) {
                    System.out.println("******DISPLAYING KD-TREE*********");
                    displayKDTree(kdTree);
                } else
                    System.out.println("KDTree does not exist! Please build before displaying");
            }
        } while (userInput != 4);
        scanner.close();
    }

    public static void buildKDTree(KDTree kdTree) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. From File");
        System.out.println("2. From Keyboard");
        System.out.print("Enter your choice: ");
        if(Integer.parseInt(scanner.nextLine()) == 1) {
            //Reading input from file
            String datafile = "data.txt";
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(datafile));
            } catch (FileNotFoundException e) {
                System.out.println("Data file not found " + e);
            }

            String ageAndSalary = null;
            while ((ageAndSalary = bufferedReader.readLine()) != null) {
                int[] ageAndSalarySplit = Arrays.stream(ageAndSalary.split(",")).map(String::trim)
                        .mapToInt(Integer::parseInt).toArray();

                kdTree.insert(ageAndSalarySplit[0], ageAndSalarySplit[1]);
            }

            bufferedReader.close();
        } else {
            int salary,age;
            System.out.print("Enter Salary: ");
            salary = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Age: ");
            age = Integer.parseInt(scanner.nextLine());
            kdTree.insert(age,salary);
        }
    }

    public static void rebuild(KDTree kdTree,ArrayList<Data> deletedList) {
        ArrayList<Data> tempList = new ArrayList<>();

        for(Data data: allValues) {
            if(!deletedList.contains(data)) {
                tempList.add(data);
            }
        }
        allValues = new ArrayList<>();
        for(Data data: tempList) {
            kdTree.insert(data.getAge(), data.getSalary());
        }
    }

    public static KDTree deleteUsingAgeAndSalary(KDTree kdTree, int age, int salary) throws IOException {
        Data deletedRecords = kdTree.delete(age, salary);
        if(deletedRecords != null) {
            ArrayList<Data> tempList = new ArrayList<>(KDTree.deletedRecords);
            if (KDTree.deletedRecords.size() == 5) {
                System.out.println("Rebuilding tree");
                kdTree = new KDTree();
                rebuild(kdTree,tempList);
            KDTree.deletedRecords = new ArrayList<>();
            }
        }

        return kdTree;
    }

    public static void displayKDTree(KDTree kdTree) {
        System.out.println(kdTree);
    }
}
