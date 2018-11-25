package com.exercise.cron;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.exercise.controller.PostsController;
import com.exercise.model.LinkPost;
import com.exercise.model.Post;
import com.exercise.model.PublicPost;
import com.exercise.req.PostRequest;
import com.exercise.services.LinkPostService;
import com.exercise.services.PostService;
import com.exercise.services.PublicPostService;
import com.exercise.util.Constants;

@Component
public class PublishPostCronJob {
  private static final Logger logger = LoggerFactory.getLogger(PostsController.class);

  @Autowired
  private PostService postService;

  @Autowired
  private PublicPostService publicPostService;

  @Autowired
  private LinkPostService linkPostService;

  @Scheduled(cron = "0/30 * * * * ?")
  public void scheduleTaskUsingCronExpression() {

    long now = System.currentTimeMillis() / 1000;
    logger.info("schedule tasks using cron jobs - " + now);
    PostRequest request = new PostRequest();
    request.setStatus(Constants.POST_STATUS_PUBLISHED);
    request.setPublishDate(new Date());
    List<Post> postList = postService.findAllToPublish(request);
    for (Post post : postList) {
      LinkPost linkPost = linkPostService.findByPost(post);
      if (linkPost != null) {
        publicPostService.edit(mappingPostToPublishPost(post, linkPost));
      } else {
        List<PublicPost> list = publicPostService.findAll();
        PublicPost publishPost = publicPostService.create(mappingPostToPublishPost(post, linkPost));
        linkPost = new LinkPost();
        linkPost.setPost(post);
        linkPost.setPublishPost(publishPost);
        linkPostService.create(linkPost);
      }
      post.setStatus(Constants.POST_STATUS_ARCHIVED);
      postService.edit(post);
    }
    logger.info("Done - " + postList);
  }

  private PublicPost mappingPostToPublishPost(final Post post, LinkPost linkPost) {
    PublicPost publishPost = new PublicPost();
    if (linkPost != null) {
      publishPost.setId(linkPost.getPublishPost().getId());
    }
    publishPost.setTitle(post.getTitle());
    publishPost.setBannerImage(post.getBannerImage());
    publishPost.setBody(post.getBody());
    publishPost.setAuthor(post.getAuthor().getUsername());
    return publishPost;
  }
}
