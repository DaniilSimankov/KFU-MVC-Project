package ru.kpfu.models;

import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "students")
@Entity
@EqualsAndHashCode(exclude = {"lessons", "students"})
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String title;

    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;
    // если чтото удаляем из List, то сначала чистит в БД а потом делает Insert чтобы вернуть оставшиеся элементы
}
