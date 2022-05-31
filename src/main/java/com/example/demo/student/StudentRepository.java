package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

/*
    @Query(
            value = "SELECT * FROM STUDENT u WHERE u.email = ?",
            nativeQuery = true)
    Collection<Student> findAllByEmail(String email);
*/

    //select * from student where email = ..
    Optional<Student> findStudentByEmail(String email);

    @Query(value = "SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findSByE(String email);

}
