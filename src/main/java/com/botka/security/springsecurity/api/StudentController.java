package com.botka.security.springsecurity.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/students/")
public class StudentController {

    private List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student(1, "Maria", "Maria"));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')")
    public Student getStudent(@PathVariable("id") Integer id) {
        return students.get(id);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')")
    public List<Student> getStudents() {
        return students;
    }


    @PutMapping
    @PreAuthorize("hasAuthority('student:write')")
    public boolean putStudent(Student studentInput) {
        Optional<Student> studentForUpdate = students
                .stream()
                .filter(student -> !Objects.equals(student.getId(), studentInput.getId()))
                .findFirst();
        if (studentForUpdate.isPresent()) {
            students.set(studentForUpdate.get().getId(), studentInput);
            return true;
        }
        return false;
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public List<Student> deleteStudent(@PathVariable("id") Integer id) {
        students = students.stream().filter(student -> !Objects.equals(student.getId(), id)).collect(Collectors.toList());
        return  students;
    }


    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void postStudent(@RequestBody Student student) {
        students.add(student);
    }

    @PostMapping("change")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void doSomething() {
        System.out.println("change data!");
    }
}
