package com.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.exercise.model.LinkPost;
import com.exercise.model.Post;

@Repository
public interface LinkPostRepository extends JpaRepository<LinkPost, Long> {
  LinkPost findByPost(Post post);
}
