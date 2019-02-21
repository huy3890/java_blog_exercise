package com.exercise.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.exercise.model.PublicPost;
import com.exercise.services.PublicPostService;
import com.froala.editor.File;
import com.froala.editor.Image;
import com.froala.editor.file.FileOptions;
import com.google.gson.Gson;

@Controller
@MultipartConfig
public class HomeController {

  @Autowired
  private PublicPostService publicPostService;

  @RequestMapping("/")
  public String index(Model model) {
    List<PublicPost> latestPosts = publicPostService.findLatestByPaging(10);
    model.addAttribute("latestPosts", latestPosts);

    List<PublicPost> latest3Posts = latestPosts.stream().limit(3).collect(Collectors.toList());
    model.addAttribute("latest3posts", latest3Posts);

    return "index";
  }

  @PostMapping("/upload_image")
  @ResponseBody
  public Map<Object, Object> process(HttpServletRequest request) throws Exception {
    Map<Object, Object> responseData;
    String fileRoute = "/public/";
    try {
      responseData = Image.upload(request, fileRoute); // Use default
                                                       // image
                                                       // validation.
    } catch (Exception e) {
      e.printStackTrace();
      responseData = new HashMap<Object, Object>();
      responseData.put("error", e.toString());
    }
    // String jsonResponseData = new Gson().toJson(responseData);
    // response.setContentType("application/json");
    // response.setCharacterEncoding("UTF-8");
    // response.getWriter().write(jsonResponseData);
    // String linkName = "http://link";
    // responseData.put("link", linkName);

    // Send response data.
    Thread.sleep(5000);
    return responseData;
  }

  @PostMapping("/delete_image")
  @ResponseBody
  public Map<Object, Object> deleteImage(HttpServletRequest request) throws Exception {
    String src = request.getParameter("src");
    Map<Object, Object> responseData = new HashMap<Object, Object>();

    try {
      Image.delete(request, src);
    } catch (Exception e) {
      e.printStackTrace();
      responseData.put("error", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    String jsonResponseData = new Gson().toJson("Success");
    // response.setContentType("application/json");
    // response.setCharacterEncoding("UTF-8");
    // response.getWriter().write(jsonResponseData);
    return responseData;
  }

  @PostMapping("/upload_file")
  @ResponseBody
  public Map<Object, Object> uploadFile(HttpServletRequest request) throws Exception {
    Map<Object, Object> responseData = new HashMap<Object, Object>();
    String fileRoute = "/public-file/";
    // No validation.
    FileOptions options = new FileOptions();
    options.setValidation(null);
    try {
      responseData = File.upload(request, fileRoute, options);
    } catch (Exception e) {
      e.printStackTrace();
      responseData.put("error", e.toString());
    }
    return responseData;
  }

  @PostMapping("/delete_file")
  @ResponseBody
  public Map<Object, Object> deleteFile(HttpServletRequest request) throws Exception {
    Map<Object, Object> responseData = new HashMap<Object, Object>();
    String src = request.getParameter("src");

    try {
      File.delete(request, src);
    } catch (Exception e) {
      e.printStackTrace();
      responseData.put("error", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
    return responseData;
  }
}
