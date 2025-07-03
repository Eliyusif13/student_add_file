package com.sadiqov.studen_add_file.service;

import com.sadiqov.studen_add_file.entitiy.Student;
import com.sadiqov.studen_add_file.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    @Value("${spring.file-upload.path}")
    private String uploadDir;


    public String uploadFile(String name, MultipartFile file) {

        try {
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(uploadDir + fileName);
            Path dirPath = Paths.get(uploadDir);

            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);

            } else if (Files.exists(path)) {
                return "Bu şəkil artıq mövcuddur: " + fileName;
            }

            Files.write(path, file.getBytes());
            String urlImage = uploadDir + fileName;
            Student student = new Student(name, urlImage);
            repository.save(student);

            return "Fayl yükləndi: " + fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getFile(String fileName) {

        try {
            Path filePath = Paths.get(uploadDir + fileName);

            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
