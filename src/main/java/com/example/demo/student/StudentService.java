package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentDaos studentdaos;
    @Autowired
    public StudentService(StudentDaos studentdaos) {
        this.studentdaos = studentdaos;
    }

    public List<Student> getStudents() {
        return studentdaos.findAll();

    }

    public void addNewStudent(Student student) {

        if (studentdaos.findStudentByEmail(student.getEmail()).isPresent()) {
            throw new IllegalArgumentException("email already exists");
        }
        studentdaos.save(student);
    }

    public void deleteStudent(long studentID) {
        if (studentdaos.findStudentById(studentID).isEmpty()) {
            throw new IllegalArgumentException("student with id: " + studentID +" does not exist");
        }
        studentdaos.deleteById(studentID);
    }

    @Transactional
    public void updateStudent(long studentID, String name , String email) {

        Student student =  studentdaos.findStudentById(studentID).orElseThrow(
                ()-> new IllegalArgumentException("student with id: " + studentID + " does not exist")
        );
        if (name != null && !name.isEmpty() && !Objects.equals(student.getName(),name)){
            student.setName(name) ;
        }

        if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(),email)){
            Optional <Student> optional = studentdaos.findStudentByEmail(email);
            if (optional.isPresent()) {
                throw new IllegalArgumentException("email already exists");
            }
            student.setEmail(email);
        }

    }
}
