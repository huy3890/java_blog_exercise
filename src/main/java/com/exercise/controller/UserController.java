package com.exercise.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.exercise.model.User;
import com.exercise.req.LoginForm;
import com.exercise.services.NotificationService;
import com.exercise.services.UserService;
import com.exercise.util.Constants;
import com.exercise.util.StringConstant;

@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private NotificationService notifyService;

  @RequestMapping(Constants.URL_USER_LOGIN)
  public String login(LoginForm loginForm) {
    return Constants.RESOURCE_USER_LOGIN;
  }

  @RequestMapping(value = Constants.URL_USER_LOGOUT, method = RequestMethod.POST)
  public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
    return "redirect:" + Constants.URL_USER_LOGIN;
  }

  @RequestMapping(Constants.URL_USER_ADD)
  public String redirectAddEditor(User user) {
    return Constants.RESOURCE_USER_ADD;
  }

  @RequestMapping(value = Constants.URL_USER_ADD, method = RequestMethod.POST)
  public String addEditor(@Valid User user, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      notifyService.addErrorMessage(Constants.ERROR_MESSAGE_LOGIN);
      return Constants.RESOURCE_USER_ADD;
    }
    final User userModel = userService.findByUsername(user.getUsername());
    if (userModel != null) {
      notifyService.addErrorMessage(Constants.USER_EXIST);
      return Constants.RESOURCE_USER_ADD;
    }
    userService.create(user, Arrays.asList(StringConstant.EDITOR.value()));
    return "redirect:" + Constants.URL_USER_LIST;
  }

  @RequestMapping(Constants.URL_USER_DELETE)
  public String deleteEditor(@PathVariable("id") Long id) {
    User user = userService.findById(id).get();
    if (user == null) {
      notifyService.addErrorMessage(Constants.USER_NOT_FOUND + id);
      return "redirect:" + Constants.URL_USER_LIST;
    }
    // userRoleService.deleteByUser(user);
    userService.deleteById(id);
    return "redirect:" + Constants.URL_USER_LIST;
  }

  @RequestMapping(Constants.URL_USER_EDIT)
  public String editEditor(@PathVariable("id") Long id, Model model) {
    User user = userService.findById(id).get();
    if (user == null) {
      notifyService.addErrorMessage(Constants.USER_NOT_FOUND + id);
      return "redirect:" + Constants.URL_USER_LIST;
    }
    model.addAttribute("user", user);
    return Constants.RESOURCE_USER_EDIT;
  }

  @RequestMapping(value = Constants.URL_USER_UPDATE, method = RequestMethod.POST)
  public String updateEditor(@Valid User user, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      notifyService.addErrorMessage(Constants.ERROR_MESSAGE_LOGIN);
      return Constants.RESOURCE_USER_EDIT;
    }
    userService.edit(user);
    return "redirect:" + Constants.URL_USER_LIST;
  }

  @RequestMapping(value = Constants.URL_USER_LIST)
  public String getListEditor(Model model) {
    List<User> userList = userService.findAll();
    model.addAttribute("users", userList);
    return Constants.RESOURCE_USER_LIST;
  }

}
