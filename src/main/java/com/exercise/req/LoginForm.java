package com.exercise.req;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {
  @Size(min = 6, max = 30, message = "Username size should be in the range [6...30]")
  private String username;

  @NotNull
  @Size(min = 6, max = 50, message = "Password size should be in the range [6...30]")
  private String password;

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
}
