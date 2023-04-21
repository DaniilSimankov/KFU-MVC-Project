package ru.kpfu.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Student implements Serializable {

    public enum Role{
        USER,
        ADMIN
    };

    public enum State{
        NOT_CONFIRMED,
        CONFIRMED,
        DELETED,
        BANNED
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique=true, nullable = false)
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @NotEmpty
    private String email;

    @Column(name = "password")
    @NotEmpty
    private String password;

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;
    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private State state;

    private String token;

    @ManyToMany
    @JoinTable(
            joinColumns = {
                    @JoinColumn(name = "student_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "id")
            }
    )
    private Set<Course> courses;
//    private List<Course> courses;
    // [особенность ManyToMany] если чтото удаляем из List,
    // то сначала чистит в БД а потом делает Insert чтобы вернуть оставшиеся элементы
}
