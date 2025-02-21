package packages.system;

    // ---------------------------------------------- STUDENT CLASS ----------------------------------------------

public class Student {
    public String name;
    public int ID;
    public int age;
    public double GPA;
    public String year;
    public String department;

    public Student() {}

    public Student(int ID, String name, int age, double GPA, String year, String department) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.GPA = GPA;
        this.year = year;
        this.department = department;
    }
}