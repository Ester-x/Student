package student_management_system;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            StudentManager studentManager = new StudentManager();

            System.out.println("Student Management System");

            while (true) {
                displayMenu();
                int choice = getUserChoice(scanner);

                switch (choice) {
                    case 1:
                        addStudent(scanner, studentManager);
                        break;
                    case 2:
                        displayStudents(studentManager);
                        break;
                    case 3:
                        updateAbsences(scanner, studentManager);
                        break;
                    case 4:
                        updateGrades(scanner, studentManager);
                        break;
                    case 5:
                        studentManager.displayAverageGrades();
                        break;
                    case 6:
                        studentManager.displayTotalAbsences();
                        break;
                    case 7:
                        studentManager.displayCourseGradesAndAbsences();
                        break;
                    case 8:
                        searchStudentsByGradeRange(scanner, studentManager);
                        break;
                    case 9:
                        rankStudentsByAbsences(studentManager);
                        break;
                    case 10:
                        System.out.println("Exiting program.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 10.");
                        break;
                }
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n1. Add Student");
        System.out.println("2. Display Students");
        System.out.println("3. Update Absences");
        System.out.println("4. Update Grades");
        System.out.println("5. Display Average Grades");
        System.out.println("6. Display Total Absences");
        System.out.println("7. Display Course Grades and Absences");
        System.out.println("8. Search Students by Grade Range");
        System.out.println("9. Rank Students by Absences");
        System.out.println("10. Exit");
        System.out.println("Enter your choice: ");
    }

    private static int getUserChoice(Scanner scanner) {
        int choice = 0;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
        scanner.nextLine();
        return choice;
    }

    private static void addStudent(Scanner scanner, StudentManager studentManager) {
        try {
            System.out.println("Enter student name: ");
            String name = scanner.nextLine();

            System.out.println("Enter student ID: ");
            String id = scanner.nextLine();

            Student student = new Student(name, id);
            studentManager.addStudent(student);
        } catch (InputMismatchException | NumberFormatException e) {
            System.err.println("Invalid input. Please enter valid data.");
            scanner.nextLine(); 
        }
    }

    private static void displayStudents(StudentManager studentManager) {
        System.out.println("Student List:");
        studentManager.displayStudents();
    }

    private static void updateAbsences(Scanner scanner, StudentManager studentManager) {
        try {
            System.out.println("Enter student ID: ");
            String studentId = scanner.nextLine();

            System.out.println("Enter course name: ");
            String courseToUpdate = scanner.nextLine();

            System.out.println("Enter new absence count: ");
            int newAbsenceCount = scanner.nextInt();
            scanner.nextLine(); 

            studentManager.updateAbsences(studentId, courseToUpdate, newAbsenceCount);
        } catch (InputMismatchException | NumberFormatException e) {
            System.err.println("Invalid input. Please enter valid data.");
            scanner.nextLine(); 
        }
    }

    private static void updateGrades(Scanner scanner, StudentManager studentManager) {
        try {
            System.out.println("Enter student ID: ");
            String studentId = scanner.nextLine();

            System.out.println("Enter course name: ");
            String courseToUpdate = scanner.nextLine();

            System.out.println("Enter new grade: ");
            double newGrade = scanner.nextDouble();
            scanner.nextLine(); 

            studentManager.updateGrades(studentId, courseToUpdate, newGrade);
        } catch (InputMismatchException | NumberFormatException e) {
            System.err.println("Invalid input. Please enter valid data.");
            scanner.nextLine(); 
        }
    }

    private static void searchStudentsByGradeRange(Scanner scanner, StudentManager studentManager) {
        try {
            System.out.println("Enter minimum grade: ");
            double minGrade = scanner.nextDouble();
            System.out.println("Enter maximum grade: ");
            double maxGrade = scanner.nextDouble();
            scanner.nextLine();

            studentManager.searchStudentsByGradeRange(minGrade, maxGrade);
        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter valid data.");
            scanner.nextLine(); 
        }
    }

    private static void rankStudentsByAbsences(StudentManager studentManager) {
        studentManager.rankStudentsByAbsences();
    }
}
