package com.exercise.util;

public class Constants {

  private Constants() {}

  public static final String ERROR_MESSAGE_LOGIN = "Please fill the form correctly!";
  public static final String SUCCESS_MESSAGE_LOGIN = "Login successful";

  public static final String URL_USER_ADD = "/users/add_editor";
  public static final String URL_USER_LIST = "/users/get_editor_listing";
  public static final String URL_USER_LOGIN = "/users/login";
  public static final String URL_USER_LOGOUT = "/users/logout";
  public static final String URL_USER_EDIT = "/users/edit/{id}";
  public static final String URL_USER_UPDATE = "/users/update_editor/{id}";
  public static final String URL_USER_DELETE = "/users/delete/{id}";

  public static final String RESOURCE_USER_ADD = "users/add_editor";
  public static final String RESOURCE_USER_LOGIN = "users/login";
  public static final String RESOURCE_USER_LIST = "users/editor_listing";
  public static final String RESOURCE_USER_EDIT = "users/edit_editor";
  public static final String USER_NOT_FOUND = "Editor not found #";

  public static final String URL_POST_CREATE = "/posts/post_create";
  public static final String URL_POST_LIST = "/posts/get_post_listing";
  public static final String URL_POST_EDIT = "/posts/post_edit";
  public static final String URL_POST_VIEW = "/posts/view/{id}";
  public static final String URL_POST_GET_ARPPOVAL = "/posts/get_approval/{id}";
  public static final String URL_POST_REJECT = "/posts/reject/{id}";
  public static final String URL_POST_PUSHLISH = "/posts/publish/{id}/{publishDate}";

  public static final String RESOURCE_POST_CREATE = "posts/post_create";
  public static final String RESOURCE_POST_LIST = "posts/post_listing";
  public static final String RESOURCE_POST_EDIT = "posts/post_edit";
  public static final String RESOURCE_POST_VIEW = "posts/view";

  public static final String POST_STATUS_DRAFT = "Draft";
  public static final String POST_STATUS_APPROVE = "Approve";
  public static final String POST_STATUS_REJECT = "Reject";
  public static final String POST_STATUS_PUBLISHED = "Published";
  public static final String POST_STATUS_ARCHIVED = "Archived";
  public static final String POST_STATUS_READY_TO_PUBLISH = "Ready to publish";

  public static final int IMAGE_MAX_SIZE = 5;
  public static final String IMAGE_ERROR_MESSAGE = "The image should be ipg, png...And size < 5MB";


}
