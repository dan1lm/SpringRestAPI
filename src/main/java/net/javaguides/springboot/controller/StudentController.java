package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student("Alexander", "Ovechkin", 8);
        return ResponseEntity.ok().header("cutom header", "header values").body(student);
    }

    // http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("first1", "last1", 1));
        students.add(new Student("first2", "last2", 2));
        students.add(new Student("first3", "last3", 3));
        students.add(new Student("first4", "last4", 4));
        return ResponseEntity.ok(students);
    }

    // Path Variable ({id} - URI Template Variable)
    // http://localhost:8080/students/1
    @GetMapping("/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentID,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
        Student student = new Student(firstName, lastName, studentID);
        return ResponseEntity.ok(student);
    }

    // Request Param
    // http://localhost:8080/students?id=1&firstName=Mike&lastName=Jones
    @GetMapping("/query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,@RequestParam String firstName, @RequestParam String lastName){
        Student student = new Student(firstName, lastName, id);
        return ResponseEntity.ok(student);
    }

    // POST
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // PUT
    @PutMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }

    // DELETE
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentID){
        System.out.println("success");
        return ResponseEntity.ok("Student deleted successfully.");
    }
}