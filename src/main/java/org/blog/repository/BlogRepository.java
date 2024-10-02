package org.blog.repository;

import org.blog.model.BlogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<BlogModel, Long> {
    // Custom query methods can be added here

    @Query(value = "SELECT * FROM blog_info WHERE title LIKE %:search% OR content LIKE %:search% OR category LIKE %:search%", nativeQuery = true)
    List<BlogModel> searchBlogs(@Param("search") String search);


}
