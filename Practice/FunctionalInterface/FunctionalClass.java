package Practice.FunctionalInterface;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.*;

public class FunctionalClass {
    public static void main(String[] args) {
        //printESquares(List.of(1, 2, 3, 5, 4, 9, 8, 7));

        List<Student> students = new ArrayList<Student>();
        students.add(new Student("Ankit", "Maths", 93));
        students.add(new Student("Ankit", "Hindi", 84));
        students.add(new Student("John", "Maths", 95));
        students.add(new Student("John", "Hindi", 95));

        printGroupByTotal(students);

    }

    private static void printEvenNumbers(List<Integer> integers) {
        integers.stream().filter(i -> i % 2 == 0).forEach(System.out::println);
    }

    private static void printESquares(List<Integer> integers) {
        integers.stream().map(i -> i * i).forEach(System.out::println);
    }

    private static void printGroupByTotal(List<Student> students) {
        System.out.println(students.stream().collect(groupingBy(Student::getName, summingInt(Student::getMarks))));

        System.out.println(students.stream().collect(groupingBy(Student::getSubject, averagingInt(Student::getMarks))));
    }
}
