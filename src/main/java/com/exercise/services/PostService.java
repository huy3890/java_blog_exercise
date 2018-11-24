package com.exercise.services;

import java.util.List;
import java.util.Optional;
import com.exercise.model.Post;
import com.exercise.req.PostRequest;


public interface PostService {
  List<Post> findAll();

  List<Post> findLatestByPaging(int record);

  Optional<Post> findById(Long id);

  Post create(Post post);

  Post edit(Post post);

  void deleteById(Long id);

  List<Post> findAllToPublish(PostRequest postRequest);
}
