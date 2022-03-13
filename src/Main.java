import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static ArrayList<Data> allValues = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        KDTree kdTree = null;
        kdTree = new KDTree();
        System.out.println("KD Tree Project");
        int userInput;
        do {
            System.out.println("1. Build KD tree from file");
            System.out.println("2. Delete Record");
            System.out.println("3. Print KD Tree");
            System.out.println("4. Exit");
            System.out.print("Enter you choice: ");
            userInput = Integer.parseInt(scanner.nextLine());
            if (userInput == 1) {
//                int salary,age;
//                System.out.print("Enter Salary: ");
//                salary = Integer.parseInt(scanner.nextLine());
//                System.out.print("Enter Age: ");
//                age = Integer.parseInt(scanner.nextLine());
//                kdTree.insert(age,salary);
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

    public static void buildKDTree(KDTree kdTree) throws IOException {
//        int[] demoKey = {25, 45, 50, 50, 50, 70, 85, 30, 25, 45, 50, 60};
//        int[] demoValue = {60, 60, 75, 100, 120, 110, 140, 260, 400, 350, 275, 260};
//        for (int i = 0; i < demoKey.length; i++) {
//            kdTree.insert(demoKey[i], demoValue[i]);
//        }
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
//        scanner.close();
    }

    public static void rebuild(KDTree kdTree,ArrayList<Data> deletedList) {
        ArrayList<Data> tempList = new ArrayList<>();
//        if(allValues.removeAll(deletedList)) {
//            for(Data entry : allValues) {
//                kdTree.insert(entry.getAge(),entry.getSalary());
//            }
//        }
        for(Data data: allValues) {
            if(!deletedList.contains(data)) {
                tempList.add(data);
                //kdTree.insert(data.getAge(), data.getSalary());
            }
        }
        allValues = new ArrayList<>();
        for(Data data: tempList) {
            kdTree.insert(data.getAge(), data.getSalary());
        }
//        Map<Data,Integer> tempHashMap = new LinkedHashMap<>();
//        for(Map.Entry<Data,Integer> entry : values.entrySet()) {
//            if(!KDTree.deletedRecords.containsKey(entry.getKey())){
//                tempHashMap.put(entry.getKey(),entry.getValue());
//            }
//        }
//        values = new LinkedHashMap<>(tempHashMap);
    }

    public static KDTree deleteUsingAgeAndSalary(KDTree kdTree, int age, int salary) throws IOException {
        Data deletedRecords = kdTree.delete(age, salary);
        if(deletedRecords != null) {
//            KDTree.deletedRecords.add(deletedRecords);
            ArrayList<Data> tempList = new ArrayList<>(KDTree.deletedRecords);
            if (KDTree.deletedRecords.size() == 5) {
                System.out.println("Rebuilding tree");
                kdTree = new KDTree();
//            KDTree.deletedRecords = new ArrayList<>(tempList);
                rebuild(kdTree,tempList);
            KDTree.deletedRecords = new ArrayList<>();
//            System.out.println(deletedRecords);
            }
        }

        return kdTree;
    }

    public static void displayKDTree(KDTree kdTree) {
        System.out.println(kdTree);
    }
}
