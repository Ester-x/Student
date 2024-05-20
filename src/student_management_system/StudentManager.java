package student_management_system;

import java.util.*;

class StudentManager {
    private Set<Student> students;

    public StudentManager() {
        students = new TreeSet<>();
        loadStudents();
    }

    private void loadStudents() {
        Random random = new Random();

        // Create John Doe with random grades and absences
        Student johnDoe = new Student("John Doe", "123");
        for (String course : Student.COURSES) {
            int randomGrade = 5 + random.nextInt(6);
            int randomAbsence = random.nextInt(5);
            johnDoe.updateGrades(course, randomGrade);
            johnDoe.updateAbsences(course, randomAbsence);
        }
        students.add(johnDoe);

        // Create Jane Smith with random grades and absences
        Student janeSmith = new Student("Jane Smith", "456");
        for (String course : Student.COURSES) {
            int randomGrade = 5 + random.nextInt(6);
            int randomAbsence = random.nextInt(5);
            janeSmith.updateGrades(course, randomGrade);
            janeSmith.updateAbsences(course, randomAbsence);
        }
        students.add(janeSmith);
    }

    public void addStudent(Student student) {
        // Check if a student with the same id already exists
        boolean studentExists = students.stream().anyMatch(s -> s.getId().equals(student.getId()));

        if (studentExists) {
            System.out.println("A student with the same ID already exists.");
        } else {
            students.add(student);
            System.out.println("Student added successfully.");
        }
    }
    //Displaying students details
    public void displayStudents() {
        for (Student student : students) {
            student.displayDetails();
        }
    }
    //Method to update Absences
    public void updateAbsences(String studentId, String course, int count) {
        Student student = findStudentById(studentId);
        if (student != null) {
            if (student.getCourses().contains(course)) {
                student.updateAbsences(course, count);
                System.out.println("Absences updated successfully.");
            } else {
                System.err.println("Error: Course does not exist.");
            }
        } else {
            System.err.println("Error: Student with ID " + studentId + " not found.");
        }
    }
    //Method to update grades
    public void updateGrades(String studentId, String course, double grade) {
        Student student = findStudentById(studentId);
        if (student != null) {
            if (student.getCourses().contains(course)) {
                student.updateGrades(course, grade);
                System.out.println("Grades updated successfully.");
            } else {
                System.err.println("Error: Course does not exist.");
            }
        } else {
            System.err.println("Error: Student with ID " + studentId + " not found.");
        }
    }
    //Method to find a student object by their id
    private Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null; 
    }
  //Method to display stuents average grades
    public void displayAverageGrades() {
        for (Student student : students) {
            double averageGrade = student.calculateAverageGrade();
            System.out.println("Student ID: " + student.getId() + ", Average Grade: " + averageGrade);
        }
    }
    
    //Method to display total absences of all students
    public void displayTotalAbsences() {
        for (Student student : students) {
            int totalAbsences = student.calculateTotalAbsences();
            System.out.println("Student ID: " + student.getId() + ", Total Absences: " + totalAbsences);
        }
    }
    
    //Method to display course grades and absences
    public void displayCourseGradesAndAbsences() {
        for (String course : Student.COURSES) {
            double courseAverageGrade = students.stream()
                    .mapToDouble(student -> student.getGrades().getOrDefault(course, 0.0))
                    .average()
                    .orElse(0.0);

            int totalCourseAbsences = students.stream()
                    .map(student -> student.getAbsences().get(course + ".Abs"))
                    .filter(Objects::nonNull)
                    .mapToInt(Integer::intValue)
                    .sum();

            System.out.println("Course: " + course + ", Average Grade: " + courseAverageGrade +
                    ", Total Absences: " + totalCourseAbsences);
        }
    }
    //This method searches students based on grade range
    public void searchStudentsByGradeRange(double minGrade, double maxGrade) {
        List<Student> filteredStudents = new ArrayList<>();
        for (Student student : students) {
            double averageGrade = student.calculateAverageGrade();
            if (averageGrade >= minGrade && averageGrade <= maxGrade) {
                filteredStudents.add(student);
            }
        }
        System.out.println("Students with average grades between " + minGrade + " and " + maxGrade + ":");
        for (Student student : filteredStudents) {
            student.displayDetails();
        }
    }

    //This method ranks students by total absences
    public void rankStudentsByAbsences() {
        List<Student> sortedStudents = new ArrayList<>(students);
        sortedStudents.sort(Comparator.comparingInt(Student::calculateTotalAbsences));
        System.out.println("Students ranked by total absences:");
        for (Student student : sortedStudents) {
            System.out.println("Student ID: " + student.getId() + ", Total Absences: " + student.calculateTotalAbsences());
        }
    }
}
