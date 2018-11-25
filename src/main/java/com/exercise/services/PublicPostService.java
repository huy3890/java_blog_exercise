package com.exercise.services;

import java.util.List;
import java.util.Optional;
import com.exercise.model.PublicPost;


public interface PublicPostService {
  List<PublicPost> findAll();

  List<PublicPost> findLatestByPaging(int record);

  Optional<PublicPost> findById(Long id);

  PublicPost create(PublicPost post);

  PublicPost edit(PublicPost post);

  void deleteById(Long id);

}
