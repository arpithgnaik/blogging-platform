package org.blog.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.blog.exception.GlobalExceptionHandler;
import org.blog.model.BlogModel;
import org.blog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService service;

    @GetMapping("/posts")
    public ResponseEntity<List<BlogModel>> getAllBlogs(@RequestParam(value = "tech",required = false) String search) {
        List<BlogModel> blogs;
        if (search==null || search.isEmpty())
            blogs = service.getAllBlogs();
        else
            blogs = service.searchBlogs(search);
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getBlogById(@Valid @PathVariable String id) {
        try {
            Long blogId = Long.valueOf(id);
//            get blog from blog id
            Optional<BlogModel> blogs = service.getBlogById(blogId);
            if (blogs.isPresent())
                return ResponseEntity.ok(blogs.get());
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");
        }catch (NumberFormatException exception) {
            return ResponseEntity.badRequest().body("Invalid blog id format");
        }
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deleteBlogById(@Valid @PathVariable String id) {
        try {
            Long blogId = Long.valueOf(id);
//            delete from database
            service.deleteBlogById(blogId);
            return ResponseEntity.ok().build();
        }catch (NumberFormatException exception) {
            return ResponseEntity.badRequest().body("Invalid blog id format");
        }
    }

    @PostMapping("/posts")
    public ResponseEntity<?> saveBlog(@Valid @RequestBody BlogModel blog) {
//        set time of creation
        blog.setCreateTime(LocalDateTime.now());
        blog.setUpdateTime(LocalDateTime.now());

//        save to database
        BlogModel blogModel = service.saveBlog(blog);
        if (blogModel==null) {
            new GlobalExceptionHandler().handleServerException("Save blog failed", HttpStatus.BAD_REQUEST);
        }
        URI location = URI.create("/posts/" + blogModel.getId());
        return ResponseEntity.created(location).body(blogModel);
    }
}
