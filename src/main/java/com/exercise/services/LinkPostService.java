package com.exercise.services;

import java.util.List;
import java.util.Optional;
import com.exercise.model.LinkPost;
import com.exercise.model.Post;


public interface LinkPostService {
  List<LinkPost> findAll();

  Optional<LinkPost> findById(Long id);

  LinkPost create(LinkPost post);

  LinkPost findByPost(Post post);

  LinkPost edit(LinkPost post);

  void deleteById(Long id);

}
