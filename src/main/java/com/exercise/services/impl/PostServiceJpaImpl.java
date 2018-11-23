package com.exercise.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.exercise.model.Post;
import com.exercise.repository.PostRepository;
import com.exercise.services.PostService;

@Service
@Transactional
public class PostServiceJpaImpl implements PostService {
  @Autowired
  private PostRepository postRepository;

  @Override
  public List<Post> findAll() {
    return this.postRepository.findAll();
  }

  @Override
  public List<Post> findLatestByPaging(int record) {
    return this.postRepository.findLatestPosts(new PageRequest(0, record));
  }

  @Override
  public Optional<Post> findById(Long id) {
    return this.postRepository.findById(id);
  }

  @Override
  public Post create(Post post) {
    return this.postRepository.save(post);
  }

  @Override
  public Post edit(Post post) {
    return this.postRepository.save(post);
  }

  @Override
  public void deleteById(Long id) {
    this.postRepository.deleteById(id);
  }
}
