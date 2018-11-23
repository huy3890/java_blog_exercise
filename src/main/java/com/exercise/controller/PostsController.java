package com.exercise.controller;

import java.io.IOException;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.exercise.model.Post;
import com.exercise.services.NotificationService;
import com.exercise.services.PostService;
import com.exercise.util.Constants;
import com.exercise.util.ImageHelper;

@Controller
public class PostsController {

  @Autowired
  private PostService postService;

  @Autowired
  private NotificationService notifyService;

  private static final Logger logger = LoggerFactory.getLogger(PostsController.class);

  @RequestMapping("/posts/view/{id}")
  public String view(@PathVariable("id") Long id, Model model) {
    Post post = postService.findById(id).get();
    if (post == null) {
      notifyService.addErrorMessage("Cannot find post #" + id);
      return "redirect:/";
    }
    model.addAttribute("post", post);
    return "posts/view";
  }

  @RequestMapping(Constants.URL_POST_CREATE)
  public String redirectCreatePost(Post post) {
    return Constants.RESOURCE_POST_CREATE;
  }

  @RequestMapping(value = Constants.URL_POST_CREATE, method = RequestMethod.POST)
  public String addEditor(@RequestParam("file") MultipartFile file, @Valid Post post,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      notifyService.addErrorMessage(Constants.ERROR_MESSAGE_LOGIN);
      return Constants.URL_POST_CREATE;
    }
    if (!file.isEmpty()) {
      try {
        byte[] bytes = file.getBytes();
        if (!ImageHelper.verifyImage(bytes)) {
          notifyService.addErrorMessage(Constants.IMAGE_ERROR_MESSAGE);
          return Constants.URL_POST_CREATE;
        }
        post.setBannerImage(bytes);
      } catch (IOException e) {
        logger.info("Cannot read file: {}", PostsController.class.toString());
      }
    }
    postService.create(post);
    return "redirect:" + Constants.URL_POST_LIST;
  }


}