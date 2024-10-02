package org.blog.service;

import lombok.RequiredArgsConstructor;
import org.blog.model.BlogModel;
import org.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository repository;

    public List<BlogModel> getAllBlogs() {
        return repository.findAll();
    }
    public List<BlogModel> searchBlogs(String search) {
        return repository.searchBlogs(search);
    }

    public Optional<BlogModel> getBlogById(Long id) {
        return repository.findById(id);
    }

    public void deleteBlogById(Long id) {
        repository.deleteById(id);
    }

    public BlogModel saveBlog(BlogModel blog) {
        return repository.save(blog);
    }
}
