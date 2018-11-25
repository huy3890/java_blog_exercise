package com.exercise.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.exercise.model.PublishPost;

@Repository
public interface PublishPostRepository extends JpaRepository<PublishPost, Long> {
  @Query("SELECT p FROM PublishPost p LEFT JOIN FETCH p.author ORDER BY p.lastModified DESC")
  List<PublishPost> findLatestPosts(Pageable pageable);
}
