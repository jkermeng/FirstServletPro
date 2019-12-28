package one;

import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        _Class _Class = new _Class(1, "class1");
        Student stu1 = new Student(1, "stu1", 24);
        stu1.set_class(_Class);
        Student stu2 = new Student(2, "stu2", 22);
        stu2.set_class(_Class);
        Student stu3 = new Student(3, "stu3", 28);
        stu3.set_class(_Class);
        Student stu4 = new Student(4, "stu4", 27);
        stu4.set_class(_Class);

        Set<Student> students = new HashSet<>();
        students.add(stu1);
        students.add(stu2);
        students.add(stu3);
        students.add(stu4);

        _Class.setStudents(students);
        one._Class _Class2 = new _Class(2, "class2");
        Student stu5 = new Student(5, "stu5", 24);
        stu5.set_class(_Class);
        Student stu6 = new Student(6, "stu6", 22);
        stu6.set_class(_Class);
        Student stu7 = new Student(7, "stu7", 28);
        stu7.set_class(_Class);
        Student stu8 = new Student(8, "stu8", 27);
        stu8.set_class(_Class);
        Set<Student> students2 = new HashSet<>();
        students2.add(stu5);
        students2.add(stu6);
        students2.add(stu7);
        students2.add(stu8);
        _Class2.setStudents(students2);
        for (Student student : students) {
            System.out.println(student);
        }

    }
}
