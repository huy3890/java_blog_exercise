package com.exercise.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.exercise.model.PublicPost;

@Repository
public interface PublicPostRepository extends JpaRepository<PublicPost, Long> {
  @Query("SELECT p FROM PublicPost p ORDER BY p.lastModified DESC")
  List<PublicPost> findLatestPosts(Pageable pageable);
}
