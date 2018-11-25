package com.exercise.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.exercise.model.PublicPost;
import com.exercise.repository.PublicPostRepository;
import com.exercise.services.PublicPostService;

@Service
@Transactional
public class PublicPostServiceJpaImpl implements PublicPostService {
  @Autowired
  private PublicPostRepository publishPostRepository;

  @Override
  public List<PublicPost> findAll() {
    return this.publishPostRepository.findAll();
  }

  @SuppressWarnings("deprecation")
  @Override
  public List<PublicPost> findLatestByPaging(int record) {
    return this.publishPostRepository.findLatestPosts(new PageRequest(0, record));
  }

  @Override
  public Optional<PublicPost> findById(Long id) {
    return this.publishPostRepository.findById(id);
  }

  @Override
  public PublicPost create(PublicPost post) {
    return this.publishPostRepository.save(post);
  }

  @Override
  public PublicPost edit(PublicPost post) {
    return this.publishPostRepository.save(post);
  }

  @Override
  public void deleteById(Long id) {
    this.publishPostRepository.deleteById(id);
  }

}
