package com.moldir.api.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;

    @GetMapping(value = "/users")
    public String getUsers(Model model) {
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);

        return "users";
    }

    @GetMapping(value = "/user/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "users_form";
    }

    @PostMapping(value = "user/save")
    public String saveUser(User user, RedirectAttributes ra) {
        service.save(user);
        ra.addFlashAttribute("message", "The user has been successfully added");

        return "redirect:/users";
    }

    @GetMapping(value = "user/update/{id}")
    public String updateUser(@PathVariable long id, Model model, RedirectAttributes ra) {
        try {
            User updatedUser = service.get(id);
            model.addAttribute("user", updatedUser);
            model.addAttribute("pageTitle", "Edit user with ID " + id);

            return "users_form";
        }
        catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());

            return "redirect:/users";

        }
    }

    @GetMapping(value = "user/delete/{id}")
    public String deleteUser(@PathVariable long id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The user ID " + id + " has been deleted.");
        }
        catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "User not found with id " + id);
        }
        return "redirect:/users";
    }
}
