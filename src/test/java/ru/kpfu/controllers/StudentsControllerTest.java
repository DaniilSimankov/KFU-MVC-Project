package ru.kpfu.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.kpfu.dto.CourseDto;
import ru.kpfu.dto.StudentDto;
import ru.kpfu.models.Course;
import ru.kpfu.models.Student;
import ru.kpfu.services.StudentsService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class StudentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentsService studentsService;

    @BeforeEach
    public void setUp() {
        when(studentsService.getAllStudents()).thenReturn(Collections.singletonList(
                        Student.builder()
                                .id(1L)
                                .firstName("Name")
                                .lastName("Surname")
                                .email("mail.m@m.2")
                                .password("password")
                                .role(Student.Role.USER)
                                .state(Student.State.CONFIRMED)
                                .courses(Set.of(Course.builder()
                                        .id(1L)
                                        .title("Web")
                                        .build()))
                                .build())
        );

    }


    @Test
    void getStudentsPage() throws Exception {
        //[{"id":1,"firstName":"Name","lastName":"Surname","email":"mail.m@m.2","courses":[{"id":1,"title":"Web"}]}]
        mockMvc.perform(get("/students/rest"))
                .andDo(print())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Name")))
                .andExpect(jsonPath("$[0].lastName", is("Surname")))
                .andExpect(jsonPath("$[0].email", is("mail.m@m.2")))
                .andExpect(jsonPath("$[0].email", is("mail.m@m.2")))
                .andExpect(jsonPath("$[0].courses[0].id", is(1)))
                .andExpect(jsonPath("$[0].courses[0].title", is("Web")))

        ;

    }
}