package com.exercise.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "publish_post", schema = "`java_blog_exercise`")
public class PublishPost {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_seq")
  @SequenceGenerator(name = "post_id_seq", sequenceName = "post_id_seq", allocationSize = 1)
  private Long id;

  @Size(min = 1, max = 255, message = "Title size should be in the range [1...255]")
  @Column(name = "title")
  private String title;

  @Column(name = "image")
  private String bannerImage;

  @Size(min = 1, message = "Title size should not be empty")
  @Column(name = "body")
  private String body;

  @JoinColumn(name = "author")
  private String author;

  @Column(name = "last_modified")
  private Date lastModified;


  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  @PrePersist
  public void addTimestamp() {
    lastModified = new Date();
  }

  @PreUpdate
  void onPersist() {
    lastModified = new Timestamp((new Date()).getTime());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBannerImage() {
    return bannerImage;
  }

  public void setBannerImage(String bannerImage) {
    this.bannerImage = bannerImage;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public PublishPost(Long id, String title, String bannerImage, String body, String author) {
    super();
    this.id = id;
    this.title = title;
    this.bannerImage = bannerImage;
    this.body = body;
    this.author = author;
  }

  public PublishPost() {
    super();
  }
}
