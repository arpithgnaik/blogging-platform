package org.blog.repository;

import org.blog.model.BlogModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<BlogModel, Long> {
    // Custom query methods can be added here
}
