package one;

import java.util.HashSet;
import java.util.Set;

public class _Class {
    private Integer id;
    private String name;
    private Set<Student> students = new HashSet();

    public _Class(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public _Class() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "_Class [id=" + id + ", name=" + name + "]";
    }

}
