package org.example;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String author;
    private String content;

    public Comment(String author, String content) {
        this.author = author;
        this.content = content;
    }

}
