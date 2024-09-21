package org.blog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "blog_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Content is mandatory")
    private String content;

    @NotBlank(message = "Category is mandatory")
    private String category;

    @NotEmpty(message = "Tags is mandatory")
    private String tags;
}
