package com.exercise.req;

import java.util.Date;

public class PostRequest {

  private String status;
  private Date publishDate;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(Date publishDate) {
    this.publishDate = publishDate;
  }

}
