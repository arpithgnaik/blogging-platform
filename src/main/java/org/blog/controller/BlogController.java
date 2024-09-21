package org.blog.controller;

import lombok.RequiredArgsConstructor;
import org.blog.model.BlogModel;
import org.blog.service.BlogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService service;

    @GetMapping("/posts")
    public ResponseEntity<List<BlogModel>> getAllBlogs() {
        List<BlogModel> blogs = service.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<BlogModel> getBlogById(@PathVariable String id) {
        Optional<BlogModel> blogs = service.getBlogById(Long.valueOf(id));
        return ResponseEntity.ok(blogs.get());
    }

    @PostMapping("/posts")
    public ResponseEntity<BlogModel> saveBlog(@RequestBody BlogModel blog) throws Exception {
        BlogModel blogModel = service.saveBlog(blog);
        if (blogModel==null) {
            throw new Exception("Save blog failed");
        }
        return ResponseEntity.ok(blogModel);
    }
}
