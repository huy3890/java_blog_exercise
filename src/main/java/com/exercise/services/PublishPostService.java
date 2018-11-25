package com.exercise.services;

import java.util.List;
import java.util.Optional;
import com.exercise.model.PublishPost;


public interface PublishPostService {
  List<PublishPost> findAll();

  List<PublishPost> findLatestByPaging(int record);

  Optional<PublishPost> findById(Long id);

  PublishPost create(PublishPost post);

  PublishPost edit(PublishPost post);

  void deleteById(Long id);

}
