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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "link_post", schema = "`java_blog_exercise`")
public class LinkPost {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  private Long id;

  @OneToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "post_id")
  private Post post;

  @OneToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "public_post_id")
  private PublicPost publicPost;

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

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public PublicPost getPublishPost() {
    return publicPost;
  }

  public void setPublishPost(PublicPost publicPost) {
    this.publicPost = publicPost;
  }

}
