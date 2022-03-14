import java.util.Comparator;

/**
 * Node to hold Age and Salary value
 */
public class Data {
    private int age;
    private int salary;

    Data() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if(this == obj)
            return true;
        Data tempData = (Data) obj;
        return this.getSalary() == tempData.getSalary() && this.getAge() == tempData.getAge();
    }

    Data(int age,int salary) {
        this.age = age;
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {return salary;}

    @Override
    public String toString() {
        return "Age: "+age+" Salary: "+salary;
    }
}
