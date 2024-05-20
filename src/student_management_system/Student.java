package student_management_system;

import java.util.*;

//Implementing comparable interface for sorting purposes
class Student implements Comparable<Student> {
    private String name;
    private String id;
    public static final List<String> COURSES = Arrays.asList("AAP", "CA", "DS", "DB", "WEB");
    private Map<String, Integer> absences;
    private Map<String, Double> grades;

    public List<String> getCourses() {
        return COURSES;
    }

    public Map<String, Integer> getAbsences() {
        return absences;
    }

    public Map<String, Double> getGrades() {
        return grades;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
    //Initializing 
    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        absences = new HashMap<>();
        grades = new HashMap<>();
        for (String course : COURSES) {
            absences.put(course + ".Abs", 0);
            grades.put(course, 4.0); 
        }
    }

    public void updateAbsences(String course, int count) {
        absences.put(course + ".Abs", count);
    }

    public void updateGrades(String course, double grade) {
        grades.put(course, grade);
    }

    public double calculateAverageGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        for (double grade : grades.values()) {
            total += grade;
        }
        return total / grades.size();
    }

    public int calculateTotalAbsences() {
        int total = 0;
        for (int count : absences.values()) {
            total += count;
        }
        return total;
    }

    public void displayDetails() {
        System.out.println("ID: " + id + ", Name: " + name + ", Courses: " + COURSES +
                ", Grades: " + grades + ", Absences: " + absences +
                ", Average Grade: " + calculateAverageGrade() + ", Total Absences: " + calculateTotalAbsences());
    }
    //Comparing one student to another based on their names
    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name);
    }
}
