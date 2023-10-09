package com.ad.blogpost.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name", nullable = false, length = 100)
    private String name;

    @Column(name = "user_email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_about")
    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
}
