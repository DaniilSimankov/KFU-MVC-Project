package ru.kpfu.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.dto.StudentDto;
import ru.kpfu.models.Student;
import ru.kpfu.services.StudentsService;

import javax.websocket.server.PathParam;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class RestController {

    private final StudentsService studentsService;

    @GetMapping("/rest")
    public ResponseEntity<List<Student>> getStudentsPage() {
        return ResponseEntity.ok(studentsService.getAllStudents());
    }
}
