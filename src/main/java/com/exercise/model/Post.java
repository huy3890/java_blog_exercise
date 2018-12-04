package com.exercise.model;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "post", schema = "`java_blog_exercise`")
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  private Long id;

  @Size(min = 1, max = 255, message = "Title size should be in the range [1...255]")
  @Column(name = "title")
  private String title;

  @Column(name = "image")
  private String bannerImage;

  @Size(min = 1, message = "Title size should not be empty")
  @Column(name = "body")
  private String body;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User author;

  @Column(name = "last_modified")
  private Date lastModified;

  @Column(name = "status")
  private String status;

  @Column(name = "publish_date")
  private Date publishDate;

  public Date getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(Date publishDate) {
    this.publishDate = publishDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

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

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public Post(Long id, String title, String bannerImage, String body, User author) {
    super();
    this.id = id;
    this.title = title;
    this.bannerImage = bannerImage;
    this.body = body;
    this.author = author;
  }

  public Post() {
    super();
  }

  @Override
  public String toString() {
    return "Post{" + "id=" + id + ", title='" + title + '\'' + ", bannerImage='" + bannerImage
        + '\'' + ", body='" + body + '\'' + ", author='" + author + '\'' + '}';
  }
}
