package org.example.services.impl;

import org.example.dao.UserDao;
import org.example.entities.User;
import org.example.enums.Role;
import org.example.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDao();

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void getAllUsers() {
        List<User> users = userDao.getAllUsers();

        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        // CSV Header
        System.out.println("ID, Name, Role, Email, Username, Active, Phone, CreatedAt, UpdatedAt, LastLogin, IsLead, Project, Location, Bio, AvatarURL");

        for (User user : users) {
            String row = String.join(", ",
                    String.valueOf(user.getId()),
                    quote(user.getName()),
                    user.getRole().name(),
                    quote(user.getEmail()),
                    quote(user.getUsername()),
                    String.valueOf(user.isActive()),
                    quote(user.getPhone()),
                    quote(user.getCreatedAt()),
                    quote(user.getUpdatedAt()),
                    quote(user.getLastLogin()),
                    user.getRole() == Role.LEAD ? "Yes" : "No",
                    user.getRole() == Role.LEAD ? quote(user.getProjectUnderManagement()) : "null",
                    quote(user.getLocation()),
                    quote(user.getBio()),
                    quote(user.getAvatarUrl())
            );

            System.out.println(row);
        }
    }

    private String quote(Object value) {
        return "\"" + (value == null ? "" : value.toString()) + "\"";
    }

}
