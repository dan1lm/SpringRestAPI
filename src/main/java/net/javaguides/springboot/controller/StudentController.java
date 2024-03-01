package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public Student getStudent(){
        Student student = new Student("Alexander", "Ovechkin", 8);
        return student;
    }

    // http://localhost:8080/students
    @GetMapping("students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("first1", "last1", 1));
        students.add(new Student("first2", "last2", 2));
        students.add(new Student("first3", "last3", 3));
        students.add(new Student("first4", "last4", 4));
        return students;
    }

    // Spring Boot REST API with Path Variable
    // {id} - URI Template Variable
    // http://localhost:8080/students/1
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studentID,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        return new Student(firstName, lastName, studentID);
    }

    // Spring boot REST API with Request Param
    // http://localhost:8080/students?id=1&firstName=Mike&lastName=Jones
    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int id,@RequestParam String firstName, @RequestParam String lastName){
        return new Student(firstName, lastName, id);
    }
}
