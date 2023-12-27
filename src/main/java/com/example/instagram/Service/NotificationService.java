package com.example.instagram.Service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.example.instagram.DTO.NotificationDTO;
import com.example.instagram.Model.Notification;
import com.example.instagram.Model.Post;
import com.example.instagram.Model.User;
import com.example.instagram.Repository.NotificationRepository;
import com.example.instagram.Repository.UserRepository;

@Service
public class NotificationService {

    @Autowired
    SimpMessagingTemplate sender;

    @Autowired
    NotificationRepository repo;

    @Autowired
    UserRepository userRepo;

    public void sendNotificationsForUser(String username) throws Exception {
        Set<Notification> notifications = repo.findNotificationsByUsername(username);
        Set<NotificationDTO> dto = new HashSet<>();
        for (Notification notification : notifications) {
            NotificationDTO obj = new NotificationDTO();
            obj.build(notification);
            dto.add(obj);
        }
        sender.convertAndSend("/topic/" + username, dto);
    }

    public Set<User> getFriends(User user) {
        Set<User> friends = user.getFollowers();
        friends.retainAll(user.getFollowing());
        return friends;
    }

    public void createPostNotification(Post post) throws Exception {
        String ownerUsername = post.getKey().getUsername();
        User user = userRepo.findById(ownerUsername).orElseThrow();
        for (User friend : getFriends(user)) {
            Notification notification = new Notification();
            notification.setFrom(user);
            notification.setTo(friend);
            notification.setType("Post");
            notification.setPost(post);
            repo.saveAndFlush(notification);
            sendNotificationsForUser(friend.getUsername());
        }
    }

    public void followNotification(User from, User to) throws Exception {
        Notification notification = new Notification();
        notification.setType("Friend Request");
        notification.setFrom(from);
        notification.setTo(to);
        repo.saveAndFlush(notification);
        sendNotificationsForUser(to.getUsername());
    }

    public void unfollowNotification(String from, String to) {
        try {
            Notification notification = repo.findFollowNotification(from, to);
            repo.deleteById(notification.getId());
            sendNotificationsForUser(to);
        } catch (Exception e) {
            System.out.println("Exception handled");
            return;
        }
    }

    public void commentNotification(Post post, String from_username) throws Exception {
        Notification notification = new Notification();
        notification.setType("Comment");
        User from = userRepo.findById(from_username).orElseThrow();
        notification.setFrom(from);
        User to = userRepo.findById(post.getKey().getUsername()).orElseThrow();
        notification.setTo(to);
        if (from.getUsername() == to.getUsername())
            return;
        notification.setPost(post);
        repo.saveAndFlush(notification);
        sendNotificationsForUser(to.getUsername());
    }

    public void removeAllNotificationsForUser(String username) {
        repo.deleteForUser(username);
    }

    public void seenNotifications(String username) {
        repo.checkNotifications(username);
    }

    /*
     * public void messageNotification(User from, User to)
     * {
     * 
     * }
     */
}
