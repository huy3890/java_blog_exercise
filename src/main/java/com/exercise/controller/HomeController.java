package com.exercise.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.exercise.model.Post;
import com.exercise.services.PostService;

@Controller
public class HomeController {

  @Autowired
  private PostService postService;

  @RequestMapping("/")
  public String index(Model model) {
    List<Post> latestPosts = postService.findLatestByPaging(10);
    model.addAttribute("latestPosts", latestPosts);

    List<Post> latest3Posts = latestPosts.stream().limit(3).collect(Collectors.toList());
    model.addAttribute("latest3posts", latest3Posts);

    return "index";
  }
}
