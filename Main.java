import java.util.*;

public class Main {
    // ---------------------------------------------- INPUT VALIDATION FUNCTIONS ----------------------------------------------

    public static String inputValidName(Scanner scanner) {
        String name;
        while (true) {
            System.out.print("Enter Student's Name: ");
            name = scanner.nextLine().trim();
            if (name.matches("[A-Za-z ]+") && !name.isEmpty()) break;
            System.out.println("Invalid input. Please enter a valid name (letters and spaces only).");
        }
        return name;
    }

    public static int inputValidID(Scanner scanner) {
        System.out.print("Enter Student's ID: ");
        String ID = scanner.nextLine();

        while (!ID.matches("\\d+") || Integer.parseInt(ID) < 0) {
            System.out.println("Invalid input. Please enter a valid integer ID.");
            System.out.print("Enter ID: ");
            ID = scanner.nextLine();
        }

        return Integer.parseInt(ID);
    }

    public static String addUniqueName(Scanner scanner, StudentSystem system) {
        String newName = inputValidName(scanner);
        while (true) {
            boolean isUnique = true;
            for (Student student : system.getStudentList()) {
                if (student.name.equals(newName)) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) break;
            System.out.println("Name already exists. Please enter a unique name.");
            newName = inputValidName(scanner);
        }
        return newName;
    }

    public static int addUniqueID(Scanner scanner, StudentSystem system) {
        int newID = inputValidID(scanner);
        while (true) {
            boolean isUnique = true;
            for (Student student : system.getStudentList()) {
                if (student.ID == newID) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) break;
            System.out.println("ID already exists. Please enter a unique ID.");
            newID = inputValidID(scanner);
        }
        return newID;
    }

    public static int inputValidAge(Scanner scanner) {
        System.out.print("Enter Student's Age: ");
        String age = scanner.nextLine();

        while (!age.matches("\\d+") || Integer.parseInt(age) < 0 || Integer.parseInt(age) > 100) {
            System.out.println("Invalid input. Please enter a valid age.");
            System.out.print("Enter Student's Age: ");
            age = scanner.nextLine();
        }

        return Integer.parseInt(age);
    }

    public static double inputValidGPA(Scanner scanner) {
        System.out.print("Enter Student's GPA (0.0 - 4.0): ");
        String GPA = scanner.nextLine();

        while (!GPA.matches("\\d+(\\.\\d+)?") || Double.parseDouble(GPA) < 0 || Double.parseDouble(GPA) > 4) {
            System.out.println("Invalid input. Please enter a valid GPA.");
            System.out.print("Enter Student's GPA (0.0 - 4.0): ");
            GPA = scanner.nextLine();
        }

        return Double.parseDouble(GPA);
    }

    public static String inputValidYear(Scanner scanner) {
        System.out.print("Enter Student's Year (First, Second, Third, Fourth): ");

        String year = scanner.nextLine();
        while (!year.matches("First|Second|Third|Fourth")) {
            System.out.println("Invalid input. Please enter a valid year.");
            System.out.print("Enter Student's Year (First, Second, Third, Fourth): ");
            year = scanner.nextLine();
        }

        return year;
    }

    public static String inputValidDepartment(Scanner scanner) {
        System.out.print("Enter Student's Department (CS - IS - AI - IT - DS): ");

        String department = scanner.nextLine();
        while (!department.matches("CS|IS|AI|IT|DS")) {
            System.out.println("Invalid input. Please enter a valid year.");
            System.out.print("Enter Student's Department (CS - IS - AI - IT - DS): ");
            department = scanner.nextLine();
        }

        return department;
    }

    public static int inputValidChoice(Scanner scanner) {
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        while (!choice.matches("\\d+") || Integer.parseInt(choice) < 0) {
            System.out.println("Invalid input. Please enter a valid number.");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();
        }

        return Integer.parseInt(choice);
    }

    // ---------------------------------------------- STUDENT SYSTEM FUNCTIONS ----------------------------------------------

    public static class Student {
        String name;
        int ID;
        int age;
        double GPA;
        String year;
        String department;
    }

    public static class StudentSystem {
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
            if (this.studentList.isEmpty()) {
                System.out.println("No students found!\n");
                return;
            }

            System.out.println("Total number of students: " + this.studentList.size());
            System.out.println();
        }

        // Calculate Average GPA
        public void calculateAverageGPA() {
            if (this.studentList.isEmpty()) {
                System.out.println("No students found!\n");
                return;
            }

            double sum = 0;
            for (Student student : this.studentList) {
                sum += student.GPA;
            }
            System.out.println("Average GPA: " + sum / this.studentList.size());
            System.out.println();
        }

        // Display Top 5 Performing Students
        public void displayTop5() {
            if (this.studentList.isEmpty()) {
                System.out.println("No students found!\n");
                return;
            }

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
            if (!isFailing) System.out.println("No failing students found!\n");
            else System.out.println();
        }

        // Generate Class Performance Summary
        public void generateSummary() {
            calculateAverageGPA();
            countTotalStudents();
            displayTop5();
            displayFailingStudents();
        }
    }

    // ---------------------------------------------- MAIN FUNCTION ----------------------------------------------

    public static void main(String[] args) {
        System.out.println("\t===> Welcome To Student Management System Application... <===\n");
        Scanner scanner = new Scanner(System.in);
        StudentSystem system = new StudentSystem();

        while (true) {
            System.out.println("Choose an option:");
            System.out.println(" 1. Add Student.");
            System.out.println(" 2. Remove Student.");
            System.out.println(" 3. Update Student.");
            System.out.println(" 4. Search Student by ID.");
            System.out.println(" 5. List and Sort Students.");
            System.out.println(" 6. Filter Students.");
            System.out.println(" 7. Count Total Students.");
            System.out.println(" 8. Calculate Average GPA.");
            System.out.println(" 9. Display Top 5 Students.");
            System.out.println(" 10. Display Failing Students.");
            System.out.println(" 11. Generate Summary.");
            System.out.println(" 0. Exit Application.");

            int choice = inputValidChoice(scanner);
            System.out.println();

            switch (choice) {
                case 1:
                    String name = addUniqueName(scanner, system);
                    int id = addUniqueID(scanner, system);
                    int age = inputValidAge(scanner);
                    double gpa = inputValidGPA(scanner);
                    String year = inputValidYear(scanner);
                    String department = inputValidDepartment(scanner);

                    system.addStudent(name, id, age, gpa, year, department);
                    break;

                case 2:
                    int removeID = inputValidID(scanner);
                    system.removeStudentByID(removeID);
                    break;

                case 3:
                    int updateID = inputValidID(scanner);

                    while (true) {
                        System.out.println("Choose an option to update:");
                        System.out.println(" 1. Student's Name.");
                        System.out.println(" 2. Student's Age.");
                        System.out.println(" 3. Student's GPA.");
                        System.out.println(" 4. Student's Year.");
                        System.out.println(" 5. Student's Department.");
                        System.out.println(" 0. Exit Update.");

                        int updateChoice = inputValidChoice(scanner);
                        if (updateChoice == 0) {
                            System.out.println();
                            break;
                        }

                        switch (updateChoice) {
                            case 1:
                                String newName = addUniqueName(scanner, system);
                                system.updateStudentByID(updateID, newName, -1, -1, null, null);
                                break;

                            case 2:
                                int newAge = inputValidAge(scanner);
                                scanner.nextLine();
                                system.updateStudentByID(updateID, null, newAge, -1, null, null);
                                break;

                            case 3:
                                double newGPA = inputValidGPA(scanner);
                                scanner.nextLine();
                                system.updateStudentByID(updateID, null, -1, newGPA, null, null);
                                break;

                            case 4:
                                String newYear = inputValidYear(scanner);
                                scanner.nextLine();
                                system.updateStudentByID(updateID, null, -1, -1, newYear, null);
                                break;

                            case 5:
                                String newDepartment = inputValidDepartment(scanner);
                                scanner.nextLine();
                                system.updateStudentByID(updateID, null, -1, -1, null, newDepartment);
                                break;

                            default:
                                System.out.println("Invalid choice, please try again.");
                        }
                    }

                    break;

                case 4:
                    int searchID = inputValidID(scanner);
                    system.searchByID(searchID);
                    break;

                case 5:
                    System.out.print("Enter sorting criteria (GPA, ID, Name, Year): ");
                    String sortBy = scanner.nextLine();

                    while (!sortBy.matches("GPA|ID|Name|Year")) {
                        System.out.println("Invalid input. Please enter a valid sorting criteria.");
                        System.out.print("Enter sorting criteria (GPA, ID, Name, Year): ");
                        sortBy = scanner.nextLine();
                    }

                    system.listAndSortAllStudents(sortBy);
                    break;

                case 6:
                    System.out.print("Choose an option to filter (Age - GPA - Year - Department): ");
                    String filterChoice = scanner.nextLine();

                    while (!filterChoice.matches("Age|GPA|Year|Department")) {
                        System.out.println("Invalid input. Please enter a valid year.");
                        System.out.print("Choose an option to filter (Age - GPA - Year - Department): ");
                        filterChoice = scanner.nextLine();
                    }

                    switch (filterChoice) {
                        case "GPA":
                            double filterGPA = inputValidGPA(scanner);
                            System.out.println();
                            system.filterByGPA(filterGPA);
                            break;

                        case "Year":
                            String filterYear = inputValidYear(scanner);
                            System.out.println();
                            system.filterByYear(filterYear);
                            break;

                        case "Department":
                            String filterDepartment = inputValidDepartment(scanner);
                            System.out.println();
                            system.filterByDepartment(filterDepartment);
                            break;

                        case "Age":
                            int filterAge = inputValidAge(scanner);
                            System.out.println();
                            system.filterByAge(filterAge);
                            break;

                        default:
                            System.out.println("Invalid choice, please try again.\n");
                    }
                    break;

                case 7:
                    system.countTotalStudents();
                    break;

                case 8:
                    system.calculateAverageGPA();
                    break;

                case 9:
                    system.displayTop5();
                    break;

                case 10:
                    system.displayFailingStudents();
                    break;

                case 11:
                    system.generateSummary();
                    break;

                case 0:
                    System.out.println("\t==> Thank you for using Student Management System Application!");
                    System.out.println("\t\t==> Exiting system...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.\n");
            }
        }
    }
}