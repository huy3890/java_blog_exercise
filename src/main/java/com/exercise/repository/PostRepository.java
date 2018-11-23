package com.exercise.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.exercise.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  @Query("SELECT p FROM Post p LEFT JOIN FETCH p.author ORDER BY p.lastModified DESC")
  List<Post> findLatestPosts(Pageable pageable);
}
