package com.exercise.cron;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.exercise.controller.PostsController;
import com.exercise.model.Post;
import com.exercise.req.PostRequest;
import com.exercise.services.PostService;
import com.exercise.util.Constants;

@Component
public class PublishPostCronJob {
  private static final Logger logger = LoggerFactory.getLogger(PostsController.class);

  @Autowired
  private PostService postService;

  @Scheduled(cron = "0/20 * * * * ?")
  public void scheduleTaskUsingCronExpression() {

    long now = System.currentTimeMillis() / 1000;
    logger.info("schedule tasks using cron jobs - " + now);
    PostRequest request = new PostRequest();
    request.setStatus(Constants.POST_STATUS_PUBLISHED);
    request.setPublishDate(new Date());
    List<Post> postList = postService.findAllToPublish(request);
    logger.info("Post - " + postList);
  }
}
