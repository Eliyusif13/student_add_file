package com.sadiqov.studen_add_file.controller;
import com.sadiqov.studen_add_file.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        String fileName = file.getOriginalFilename();
        String response = service.uploadFile(name, file);


        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", "/second-page.html?fileName=" + fileName)  // fileName ilə yönləndirmə
                .body(response);

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
