package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    // Path Variable ({id} - URI Template Variable)
    // http://localhost:8080/students/1
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable("id") int studentID,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        return new Student(firstName, lastName, studentID);
    }

    // Request Param
    // http://localhost:8080/students?id=1&firstName=Mike&lastName=Jones
    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int id,@RequestParam String firstName, @RequestParam String lastName){
        return new Student(firstName, lastName, id);
    }

    // POST
    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    // PUT
    @PutMapping("students/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudent(@RequestBody Student student,@PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    // Delete
    @DeleteMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentID){
        System.out.println("success");
        return "Student deleted successfully.";
    }
}