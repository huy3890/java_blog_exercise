package com.exercise.services.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exercise.services.NotificationService;
import com.exercise.util.NotificationMessage;
import com.exercise.util.NotificationMessageType;

@Service
public class NotificationServiceImpl implements NotificationService {

  public static final String NOTIFY_MSG_SESSION_KEY = "siteNotificationMessages";

  @Autowired
  private HttpSession httpSession;

  @Override
  public void addInfoMessage(String msg) {
    addNotificationMessage(NotificationMessageType.INFO, msg);
  }

  @Override
  public void addErrorMessage(String msg) {
    addNotificationMessage(NotificationMessageType.ERROR, msg);
  }

  @SuppressWarnings("unchecked")
  private void addNotificationMessage(NotificationMessageType type, String msg) {
    List<NotificationMessage> notifyMessages =
        (List<NotificationMessage>) httpSession.getAttribute(NOTIFY_MSG_SESSION_KEY);
    if (notifyMessages == null) {
      notifyMessages = new ArrayList<NotificationMessage>();
    }
    notifyMessages.clear();
    notifyMessages.add(new NotificationMessage(type, msg));
    httpSession.setAttribute(NOTIFY_MSG_SESSION_KEY, notifyMessages);
  }
}
