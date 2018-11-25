package com.exercise.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.exercise.model.PublishPost;
import com.exercise.repository.PublishPostRepository;
import com.exercise.services.PublishPostService;

@Service
@Transactional
public class PublishPostServiceJpaImpl implements PublishPostService {
  @Autowired
  private PublishPostRepository publishPostRepository;

  @Override
  public List<PublishPost> findAll() {
    return this.publishPostRepository.findAll();
  }

  @SuppressWarnings("deprecation")
  @Override
  public List<PublishPost> findLatestByPaging(int record) {
    return this.publishPostRepository.findLatestPosts(new PageRequest(0, record));
  }

  @Override
  public Optional<PublishPost> findById(Long id) {
    return this.publishPostRepository.findById(id);
  }

  @Override
  public PublishPost create(PublishPost post) {
    return this.publishPostRepository.save(post);
  }

  @Override
  public PublishPost edit(PublishPost post) {
    return this.publishPostRepository.save(post);
  }

  @Override
  public void deleteById(Long id) {
    this.publishPostRepository.deleteById(id);
  }

}
