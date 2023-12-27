package com.example.instagram.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.instagram.Service.NotificationService;

@Controller
public class NotificationController {

    @Autowired
    NotificationService service;

    @MessageMapping("/getNotifications")
    public void sendNotification(String username) throws Exception{
        System.out.println("called with username " + username);
        service.sendNotificationsForUser(username);
    }

    @PostMapping("/checkNotifications")
    public @ResponseBody String checkedNotifications(@RequestBody String username)
    {
        service.seenNotifications(username);
        return "Seen notifications for " + username;
    }
}
