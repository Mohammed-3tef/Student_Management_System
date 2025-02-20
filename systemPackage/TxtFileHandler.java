package systemPackage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

    // --------------------------------- TXT FILE HANDLER CLASS ---------------------------------

public class TxtFileHandler {
    private static final String OUTPUT_TXT_FILE = "Final_Report.txt";

    public static void writeTxtFile(StudentSystem students) {
        try (FileWriter writer = new FileWriter(OUTPUT_TXT_FILE)) {
            if (students.getStudentList().isEmpty()) {
                System.out.println("\nNo students to write to txt file.\n");
                return;
            }

            writer.write("------------------------------ CLASS PERFORMANCE ------------------------------\n");
            students.sortStudentsBy("GPA");
            ArrayList<Student> studentList = students.getStudentList();

            writer.write("\nTotal number of students: " + studentList.size());
            writer.write("\n\n");

            double sum = 0;
            for (Student student : studentList) {
                sum += student.GPA;
            }
            writer.write("Average GPA: " + sum / studentList.size());
            writer.write("\n\n");

            int first = 0, second = 0, third = 0, fourth = 0;
            for (Student student : studentList) {
                switch (student.year) {
                    case "First" -> first++;
                    case "Second" -> second++;
                    case "Third" -> third++;
                    case "Fourth" -> fourth++;
                }
            }
            writer.write("Year-wise Student Count:");
            writer.write("\n - First Year: " + first);
            writer.write("\n - Second Year: " + second);
            writer.write("\n - Third Year: " + third);
            writer.write("\n - Fourth Year: " + fourth);
            writer.write("\n\n");

            writer.write("Top 5 Performing Students:");
            int count = 1;
            for (Student student : studentList) {
                if (student.GPA < 2.0 || count > 5) break;
                writer.write("\n " + (count++) + ". ");
                writer.write(student.name);
                writer.write(", ID: " + student.ID);
                writer.write(", Age: " + student.age);
                writer.write(", GPA: " + student.GPA);
                writer.write(", Year: " + student.year);
                writer.write(", Department: " + student.department);
            }
            writer.write("\n\n");

            writer.write("Failing students who have GPA less than 2.0:");
            boolean isFailing = false;
            for (Student student : studentList) {
                if (student.GPA < 2.0) {
                    isFailing = true;
                    writer.write("\n - " + student.name);
                    writer.write(", ID: " + student.ID);
                    writer.write(", Age: " + student.age);
                    writer.write(", GPA: " + student.GPA);
                    writer.write(", Year: " + student.year);
                    writer.write(", Department: " + student.department);
                }
            }
            if (!isFailing) writer.write(" No failing students found!\n");
            else writer.write("\n\n");

            writer.flush();
            System.out.println("\nTxt file written successfully.");
            System.out.println("File Path: " + OUTPUT_TXT_FILE);
        }
        catch (IOException e) {
            System.err.println("Error writing Txt file: " + e.getMessage());
        }
        System.out.println();
    }
}
