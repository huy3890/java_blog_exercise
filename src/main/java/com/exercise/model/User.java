package com.exercise.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users", schema = "`convertium_assignment`")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
  @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
  private Long id;

  @Size(min = 6, max = 30, message = "Username size should be in the range [6...30]")
  @Column(name = "user_name", nullable = false, length = 30, unique = true)
  private String username;

  @Size(min = 6, max = 60, message = "Password size should be in the range [6...60]")
  @Column(name = "pass_word", length = 60)
  private String password;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "last_modified")
  private Date lastModified;

  @OneToMany(mappedBy = "author")
  private Set<Post> posts = new HashSet<Post>();

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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Set<Post> getPosts() {
    return posts;
  }

  public void setPosts(Set<Post> posts) {
    this.posts = posts;
  }

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password
        + '\'' + ", fullName='" + fullName + '\'' + '}';
  }

  public User(Long id, String username, String password, String fullName) {
    super();
    this.id = id;
    this.username = username;
    this.password = password;
    this.fullName = fullName;
  }

  public User() {
    super();
  }
}
