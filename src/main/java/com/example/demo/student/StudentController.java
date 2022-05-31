package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String index() {
        return "hello world";
    }

    @GetMapping("/get")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path="/delete/{id}")
    public void deleteStudentById(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
    }

    @PutMapping(path="/update/{id}")
    public void updateStudentById(@PathVariable("id") Long studentId,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }
}
