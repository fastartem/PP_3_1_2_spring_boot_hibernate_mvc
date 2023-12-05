package com.example.SpringBootHibernateMVC.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.SpringBootHibernateMVC.web.model.User;
import com.example.SpringBootHibernateMVC.web.service.UserService;

import javax.validation.Valid;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/", "/users"})
    public String printUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @GetMapping("/user-add")
    public String addUserForm(User user) {
        return "user-add";
    }

    @PostMapping("/user-add")
    public String addUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user-add";
        }

        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/user-delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update")
    public String updateUserForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user-update";
        }

        userService.update(user);
        return "redirect:/users";
    }
}