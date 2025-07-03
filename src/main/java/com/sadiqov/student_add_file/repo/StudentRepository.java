package com.sadiqov.student_add_file.repo;

import com.sadiqov.student_add_file.entitiy.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
