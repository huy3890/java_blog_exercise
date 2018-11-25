package com.exercise.services.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.exercise.model.LinkPost;
import com.exercise.model.Post;
import com.exercise.repository.LinkPostRepository;
import com.exercise.services.LinkPostService;

@Service
@Transactional
public class LinkPostServiceJpaImpl implements LinkPostService {
  @Autowired
  private LinkPostRepository linkPostRepository;

  @Override
  public List<LinkPost> findAll() {
    return this.linkPostRepository.findAll();
  }

  @Override
  public Optional<LinkPost> findById(Long id) {
    return this.linkPostRepository.findById(id);
  }

  @Override
  public LinkPost create(LinkPost post) {
    return this.linkPostRepository.save(post);
  }

  @Override
  public LinkPost edit(LinkPost post) {
    return this.linkPostRepository.save(post);
  }

  @Override
  public void deleteById(Long id) {
    this.linkPostRepository.deleteById(id);
  }

  @Override
  public LinkPost findByPost(Post post) {
    return this.linkPostRepository.findByPost(post);
  }

}
