package com.exercise.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.exercise.model.Post;
import com.exercise.model.User;
import com.exercise.services.NotificationService;
import com.exercise.services.PostService;
import com.exercise.services.UserService;
import com.exercise.util.Constants;
import com.exercise.util.DateHelper;
import com.exercise.util.ImageHelper;
import com.exercise.util.StringConstant;

@Controller
public class PostsController {

  @Autowired
  private PostService postService;

  @Autowired
  private NotificationService notifyService;

  @Autowired
  private UserService userService;

  private static final Logger logger = LoggerFactory.getLogger(PostsController.class);

  @RequestMapping(Constants.URL_POST_VIEW)
  public String view(@PathVariable("id") Long id, Model model) {
    Post post = postService.findById(id).get();
    if (post == null) {
      notifyService.addErrorMessage("Cannot find post #" + id);
      return "redirect:" + Constants.URL_POST_LIST;
    }
    model.addAttribute("post", post);
    return Constants.RESOURCE_POST_VIEW;
  }

  @RequestMapping(Constants.URL_POST_GET_ARPPOVAL)
  public String getApproval(@PathVariable("id") Long id, Model model) {
    Post post = postService.findById(id).get();
    if (post == null) {
      notifyService.addErrorMessage("Cannot find post #" + id);
      return "redirect:" + Constants.URL_POST_LIST;
    }
    post.setStatus(Constants.POST_STATUS_READY_TO_PUBLISH);
    postService.edit(post);
    return "redirect:" + Constants.URL_POST_VIEW;
  }

  @RequestMapping(Constants.URL_POST_REJECT)
  public String rejectPost(@PathVariable("id") Long id, Model model) {
    Post post = postService.findById(id).get();
    if (post == null) {
      notifyService.addErrorMessage("Cannot find post #" + id);
      return "redirect:" + Constants.URL_POST_LIST;
    }
    post.setStatus(Constants.POST_STATUS_REJECT);
    postService.edit(post);
    return "redirect:" + Constants.URL_POST_VIEW;
  }

  @RequestMapping(Constants.URL_POST_PUSHLISH)
  public String publishPost(@PathVariable("id") Long id,
      @PathVariable("publishDate") String publishDate, Model model) {
    Post post = postService.findById(id).get();
    if (post == null) {
      notifyService.addErrorMessage("Cannot find post #" + id);
      return "redirect:" + Constants.URL_POST_LIST;
    }
    post.setPublishDate(DateHelper.convertDateFromUnixTime(publishDate));
    post.setStatus(Constants.POST_STATUS_PUBLISHED);
    postService.edit(post);
    return "redirect:" + Constants.URL_POST_VIEW;
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
    final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    final User user = userService.findByUsername(auth.getName());
    post.setAuthor(user);
    if (!file.isEmpty()) {
      try {
        byte[] bytes = file.getBytes();
        if (!ImageHelper.verifyImage(bytes)) {
          notifyService.addErrorMessage(Constants.IMAGE_ERROR_MESSAGE);
          return Constants.URL_POST_CREATE;
        }
        // post.setBannerImage(Base64.getEncoder().encode(bytes));
        post.setBannerImage(org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(bytes));
      } catch (IOException e) {
        logger.info("Cannot read file: {}", PostsController.class.toString());
      }
    }
    post.setStatus(Constants.POST_STATUS_DRAFT);
    final Post returnData = postService.create(post);
    return "redirect:" + Constants.URL_POST_VIEW_NO_ID + returnData.getId();
  }

  @RequestMapping(Constants.URL_POST_EDIT)
  public String redirectEditPost(@PathVariable("id") Long id, Model model) {
    Post post = postService.findById(id).get();
    if (post == null) {
      notifyService.addErrorMessage(Constants.POST_NOT_FOUND + id);
      return "redirect:" + Constants.URL_POST_LIST;
    }
    model.addAttribute("post", post);
    return Constants.RESOURCE_POST_EDIT;
  }

  @RequestMapping(value = Constants.URL_POST_EDIT, method = RequestMethod.POST)
  public String editPost(@RequestParam("file") MultipartFile file, @Valid Post post,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      notifyService.addErrorMessage(Constants.ERROR_MESSAGE_LOGIN);
      return Constants.URL_POST_EDIT;
    }
    final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findByUsername(auth.getName());
    post.setAuthor(user);
    if (!file.isEmpty()) {
      try {
        byte[] bytes = file.getBytes();
        if (!ImageHelper.verifyImage(bytes)) {
          notifyService.addErrorMessage(Constants.IMAGE_ERROR_MESSAGE);
          return Constants.URL_POST_EDIT;
        }
        // post.setBannerImage(Base64.getEncoder().encode(bytes));
        post.setBannerImage(org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(bytes));
      } catch (IOException e) {
        logger.info("Cannot read file: {}", PostsController.class.toString());
      }
    }
    post.setStatus(Constants.POST_STATUS_DRAFT);
    final Post returnData = postService.edit(post);
    return "redirect:" + Constants.URL_POST_VIEW_NO_ID + returnData.getId();
  }

  @RequestMapping(value = Constants.URL_POST_LIST)
  public String getListEditor(Model model) {
    List<Post> postList = new ArrayList<Post>();
    final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
      if (authorities.contains(new SimpleGrantedAuthority(StringConstant.EDITOR.value()))) {
        final User user = userService.findByUsername(auth.getName());
        postList = postService.findByAuthor(user);
      } else if (authorities.contains(new SimpleGrantedAuthority(StringConstant.ADMIN.value()))) {
        postList = postService.findAll();
      }
    }
    // List<Post> postList = postService.findAllToPublish(request);
    model.addAttribute("posts", postList);
    return Constants.RESOURCE_POST_LIST;
  }


}
