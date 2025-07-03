package com.sadiqov.studen_add_file.controller;
import com.sadiqov.studen_add_file.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final StudentService service;

    @Autowired
    public FileController(StudentService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("name") String name,
                                             @RequestParam("file") MultipartFile file) {
        String response = service.uploadFile(name, file);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{filename}")
    public ResponseEntity<byte[]> getFile(@PathVariable String filename) {
        byte[] fileContent = service.getFile(filename);
        if (fileContent != null) {
            return ResponseEntity.ok(fileContent);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }
}
