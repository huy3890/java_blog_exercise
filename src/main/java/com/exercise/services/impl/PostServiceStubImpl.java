package com.exercise.services.impl;
// package com.convertium.services.impl;
//
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Objects;
// import java.util.Optional;
// import java.util.stream.Collectors;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import com.convertium.model.Post;
// import com.convertium.model.User;
// import com.convertium.services.PostService;
//
// @Service
// @Transactional
// public class PostServiceStubImpl implements PostService {
//
// @SuppressWarnings("serial")
// private List<Post> posts = new ArrayList<Post>() {
// {
// add(new Post(1l, "First Post", "<p>Line #1.</p><p>Line #1</p>", null,
// new User(1l, "huypham", "123456", "Huy Pham"), null));
// add(new Post(2l, "First Post", "<p>Line #2.</p><p>Line #2</p>", null,
// new User(1l, "hoanghai", "123456", "Hoang Hai"), null));
// }
// };
//
// @Override
// public List<Post> findAll() {
// return this.posts;
// }
//
// @Override
// public List<Post> findLatest5() {
// return this.posts.stream().sorted((a, b) -> b.getId().compareTo(a.getId())).limit(5)
// .collect(Collectors.toList());
// }
//
// @Override
// public Optional<Post> findById(Long id) {
// return Optional.ofNullable(
// this.posts.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst().orElse(null));
// }
//
// @Override
// public Post create(Post post) {
// post.setId(this.posts.stream().mapToLong(p -> p.getId()).max().getAsLong() + 1);
// this.posts.add(post);
// return post;
// }
//
// @Override
// public Post edit(Post post) {
// for (int i = 0; i < this.posts.size(); i++) {
// if (Objects.equals(this.posts.get(i).getId(), post.getId())) {
// this.posts.set(i, post);
// return post;
// }
// }
// throw new RuntimeException("Post not found: " + post.getId());
// }
//
// @Override
// public void deleteById(Long id) {
// for (int i = 0; i < this.posts.size(); i++) {
// if (Objects.equals(this.posts.get(i).getId(), id)) {
// this.posts.remove(i);
// return;
// }
// }
// throw new RuntimeException("Post not found: " + id);
// }
//
// }
