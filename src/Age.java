import java.util.Comparator;

public class Age implements Comparator<Age> {
    private int age;

    Age() {
    }

    Age(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }


    @Override
    public String toString() {
        return Integer.toString(age);
    }

    @Override
    public int compare(Age o1, Age o2) {
        if (o1.age < o2.age) {
            return 1;
        } else if (o1.age == o2.age) {
            return 0;
        } else {
            return -1;
        }
    }
}
