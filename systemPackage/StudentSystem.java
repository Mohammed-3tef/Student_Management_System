package systemPackage ;
import java.util.ArrayList;
import java.util.Comparator;

    // ---------------------------------------------- STUDENT SYSTEM CLASS ----------------------------------------------

public class StudentSystem {
    private ArrayList<Student> studentList;

    public StudentSystem() {this.studentList = new ArrayList<>();}
    public ArrayList<Student> getStudentList() {return studentList;}

    // Sort Students
    public void sortStudentsBy(String sortBy) {
        switch (sortBy) {
            case "GPA" ->
                    this.studentList.sort((firstStudent, secondStudent) -> Double.compare(secondStudent.GPA, firstStudent.GPA));
            case "ID" ->
                    this.studentList.sort(Comparator.comparingInt(firstStudent -> firstStudent.ID));
            case "Name" ->
                    this.studentList.sort(Comparator.comparing(firstStudent -> firstStudent.name));
            case "Department" ->
                    this.studentList.sort(Comparator.comparing(firstStudent -> firstStudent.department));
            case "Year" ->
                    this.studentList.sort(Comparator.comparingInt(student -> switch (student.year) {
                        case "First" -> 1;
                        case "Second" -> 2;
                        case "Third" -> 3;
                        case "Fourth" -> 4;
                        default -> 0;
                    }));
        }
    }

    public void addStudent(String newName, int newID, int newAge, double newGPA, String newYear, String newDepartment) {
        Student newStudent = new Student();
        newStudent.name = newName;
        newStudent.ID = newID;
        newStudent.age = newAge;
        newStudent.GPA = newGPA;
        newStudent.year = newYear;
        newStudent.department = newDepartment;
        this.studentList.add(newStudent);
        System.out.println("Student added successfully!\n");
    }

    public void removeStudentByID(int ID) {
        for (int i = 0; i < this.studentList.size(); i++) {
            if (this.studentList.get(i).ID == ID) {
                this.studentList.remove(i);
                System.out.println("Student removed successfully!\n");
                return;
            }
        }
        System.out.println("Student not found!\n");
    }

    // Update Student Information Using ID
    public void updateStudentByID(int ID, String newName, int newAge, double newGPA, String newYear, String newDepartment) {
        for (Student student : this.studentList) {
            if (student.ID == ID) {
                if (newName != null) student.name = newName;
                if (newAge != -1) student.age = newAge;
                if (newGPA != -1) student.GPA = newGPA;
                if (newYear != null) student.year = newYear;
                if (newDepartment != null) student.department = newDepartment;
                System.out.println("Student updated successfully!\n");
                return;
            }
        }

        System.out.println("Student not found!\n");
    }

    // Search Student Using ID
    public void searchByID(int ID) {
        for (Student student : this.studentList) {
            if (student.ID == ID) {
                System.out.println("Student found!");
                System.out.print("Name: " + student.name);
                System.out.print(", Age: " + student.age);
                System.out.print(", GPA: " + student.GPA);
                System.out.print(", Year: " + student.year);
                System.out.println(", Department: " + student.department);
                System.out.println();
                return;
            }
        }
        System.out.println("Student not found!\n");
    }

    // List and Sort All Students
    public void listAndSortAllStudents(String sortBy) {
        if (this.studentList.isEmpty()) {
            System.out.println("No students found!\n");
            return;
        }

        switch (sortBy) {
            case "GPA" -> sortStudentsBy("GPA");
            case "ID" -> sortStudentsBy("ID");
            case "Name" -> sortStudentsBy("Name");
            case "Year" -> sortStudentsBy("Year");
            case "Department" -> sortStudentsBy("Department");
        }

        System.out.println("\nList of Students:");
        for (Student student : this.studentList) {
            System.out.print(" " + student.name);
            System.out.print(", ID: " + student.ID);
            System.out.print(", Age: " + student.age);
            System.out.print(", GPA: " + student.GPA);
            System.out.print(", Year: " + student.year);
            System.out.println(", Department: " + student.department);
        }
        System.out.println();
    }

    // Filter Students by Age
    public void filterByAge(int age) {
        System.out.println("Students with age of " + age + ":");
        boolean isFound = false;
        for (Student student : this.studentList) {
            if (student.age == age) {
                isFound = true;
                System.out.print(" " + student.name);
                System.out.print(", ID: " + student.ID);
                System.out.print(", GPA: " + student.GPA);
                System.out.print(", Year: " + student.year);
                System.out.println(", Department: " + student.department);
            }
        }
        if (!isFound) System.out.println("No students found with age of " + age + "!\n");
        else System.out.println();
    }

    // Filter Students by GPA
    public void filterByGPA(double GPA) {
        System.out.println("Students with GPA of " + GPA + ":");
        boolean isFound = false;
        for (Student student : this.studentList) {
            if (student.GPA == GPA) {
                isFound = true;
                System.out.print(" " + student.name);
                System.out.print(", ID: " + student.ID);
                System.out.print(", Age: " + student.age);
                System.out.print(", Year: " + student.year);
                System.out.println(", Department: " + student.department);
            }
        }
        if (!isFound) System.out.println("No students found with GPA of " + GPA + "!\n");
        else System.out.println();
    }

    // Filter Students by Year
    public void filterByYear(String year) {
        System.out.println("Students in " + year + " year:");
        boolean isFound = false;
        for (Student student : this.studentList) {
            if (student.year.equals(year)) {
                isFound = true;
                System.out.print(" " + student.name);
                System.out.print(", ID: " + student.ID);
                System.out.print(", Age: " + student.age);
                System.out.print(", GPA: " + student.GPA);
                System.out.println(", Department: " + student.department);
            }
        }
        if (!isFound) System.out.println("No students found in " + year + " year!\n");
        else System.out.println();
    }

    // Filter Students by Department
    public void filterByDepartment(String department) {
        System.out.println("Students in " + department + " department:");
        boolean isFound = false;
        for (Student student : this.studentList) {
            if (student.department.equals(department)) {
                isFound = true;
                System.out.print(" " + student.name);
                System.out.print(", ID: " + student.ID);
                System.out.print(", Age: " + student.age);
                System.out.print(", GPA: " + student.GPA);
                System.out.println(", Year: " + student.year);
            }
        }
        if (!isFound) System.out.println("No students found in " + department + " department!\n");
        else System.out.println();
    }

    // Count the Total Number of Students
    public void countTotalStudents() {
        System.out.println("Total number of students: " + this.studentList.size());
        System.out.println();
    }

    // Calculate Average GPA
    public void calculateAverageGPA() {
        double sum = 0;
        for (Student student : this.studentList) {
            sum += student.GPA;
        }
        System.out.println("Average GPA: " + sum / this.studentList.size());
        System.out.println();
    }

    // Display Top 5 Performing Students
    public void displayTop5() {
        sortStudentsBy("GPA");

        System.out.println("Top 5 Performing Students:");
        int count = 1;
        for (Student student : this.studentList) {
            if (student.GPA < 2.0) break;
            System.out.print(" " + (count++) + ". ");
            System.out.print(student.name);
            System.out.print(", ID: " + student.ID);
            System.out.print(", Age: " + student.age);
            System.out.print(", GPA: " + student.GPA);
            System.out.print(", Year: " + student.year);
            System.out.println(", Department: " + student.department);
        }
        System.out.println();
    }


    // Display Failing Students
    public void displayFailingStudents() {
        System.out.println("Failing students who have GPA less than 2.0:");
        boolean isFailing = false;
        for (Student student : this.studentList) {
            if (student.GPA < 2.0) {
                isFailing = true;
                System.out.print(" " + student.name);
                System.out.print(", ID: " + student.ID);
                System.out.print(", Age: " + student.age);
                System.out.print(", GPA: " + student.GPA);
                System.out.print(", Year: " + student.year);
                System.out.println(", Department: " + student.department);
            }
        }
        if (!isFailing) System.out.println(" No failing students found!\n");
        else System.out.println();
    }

    // Count Students by Year
    public void countStudentsByYear() {
        int first = 0, second = 0, third = 0, fourth = 0;
        for (Student student : studentList) {
            switch (student.year) {
                case "First" -> first++;
                case "Second" -> second++;
                case "Third" -> third++;
                case "Fourth" -> fourth++;
            }
        }
        System.out.println("Year-wise Student Count:");
        System.out.println(" - First Year: " + first);
        System.out.println(" - Second Year: " + second);
        System.out.println(" - Third Year: " + third);
        System.out.println(" - Fourth Year: " + fourth);
        System.out.println();
    }

    // Generate Class Performance Summary
    public void generateSummary() {
        if (this.studentList.isEmpty()) {
            System.out.println("No students found!\n");
            return;
        }
        calculateAverageGPA();
        countTotalStudents();
        countStudentsByYear();
        displayTop5();
        displayFailingStudents();
    }
}
