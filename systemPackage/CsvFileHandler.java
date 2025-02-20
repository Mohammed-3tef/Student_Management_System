package systemPackage;
import java.io.*;
import java.util.Scanner;

    // --------------------------------- CSV FILE HANDLER CLASS ---------------------------------

public class CsvFileHandler {
    private static String INPUT_CSV_FILE;
    private static final String OUTPUT_CSV_FILE = "Final_Students.csv";

    public static Student fromCSV(String csvLine) {
        csvLine = csvLine.replaceAll("\"", "");
        String[] parts = csvLine.split(",");

        return new Student(
                Integer.parseInt(parts[0]),     // ID
                parts[1],                       // Name
                Integer.parseInt(parts[2]),     // Age
                Double.parseDouble(parts[3]),   // GPA
                parts[4],                       // Year
                parts[5]                        // Department
        );
    }

    public static StudentSystem readCsvFile() {
        StudentSystem students = new StudentSystem();
        Scanner scanner = new Scanner(System.in);
        INPUT_CSV_FILE = InputValidator.getFilePath(scanner, ".csv");


        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_CSV_FILE))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                Student student = fromCSV(line);
                boolean isUnique = true;
                for (Student s : students.getStudentList()) {
                    if (s.ID == student.ID) {
                        System.out.println("ID already exists. Please enter a unique ID.");
                        isUnique = false;
                    }
                    if (s.name.equals(student.name)) {
                        System.out.println("Name already exists. Please enter a unique name.");
                        isUnique = false;
                    }
                }
                if (isUnique) {
                    if (student.year.equals("First") || student.year.equals("Second")) student.department = "General";
                    students.addStudent(student.name, student.ID, student.age, student.GPA, student.year, student.department, true);
                }
            }
            System.out.println("\nCSV file read successfully.\n");
        }
        catch (IOException e) {
            System.err.println("\nError reading CSV file: " + e.getMessage());
            System.out.println();
        }
        return students;
    }

    public static void writeCsvFile(StudentSystem students) {
        try (FileWriter writer = new FileWriter(OUTPUT_CSV_FILE)) {
            if (students.getStudentList().isEmpty()) {
                System.out.println("\nNo students to write to CSV file.\n");
                return;
            }

            students.sortStudentsBy("ID");

            writer.write("\"ID\",\"Name\",\"Age\",\"GPA\",\"Year\",\"Department\"\n");
            for (Student student : students.getStudentList()) {
                writer.write("\"" + student.ID + "\",");
                writer.write("\"" + student.name + "\",");
                writer.write("\"" + student.age + "\",");
                writer.write("\"" + student.GPA + "\",");
                writer.write("\"" + student.year + "\",");
                writer.write("\"" + student.department + "\"\n");
            }
            writer.flush();
            System.out.println("\nCSV file written successfully.");
            System.out.println("File Path: " + OUTPUT_CSV_FILE);
        }
        catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
        System.out.println();
    }
}
