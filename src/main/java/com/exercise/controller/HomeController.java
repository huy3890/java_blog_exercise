package com.exercise.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.exercise.model.PublicPost;
import com.exercise.services.PublicPostService;

@Controller
public class HomeController {

  @Autowired
  private PublicPostService publicPostService;

  @RequestMapping("/")
  public String index(Model model) {
    List<PublicPost> latestPosts = publicPostService.findLatestByPaging(10);
    model.addAttribute("latestPosts", latestPosts);

    List<PublicPost> latest3Posts = latestPosts.stream().limit(3).collect(Collectors.toList());
    model.addAttribute("latest3posts", latest3Posts);

    return "index";
  }
}
