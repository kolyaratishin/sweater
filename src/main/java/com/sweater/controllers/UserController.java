package com.sweater.controllers;

import com.sweater.models.Role;
import com.sweater.models.User;
import com.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userService.findAll());
        return "userList";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user,Model model){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam("username")String username,
            @RequestParam Map<String,String> form,
            @RequestParam("userId") User user
            ){
        userService.saveUser(username,form,user);
        return "redirect:/user";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("email",user.getEmail());
        model.addAttribute("username",user.getUsername());
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String email,
            @RequestParam String password
    ){
        userService.updateProfile(user,email,password);
        return "redirect:/user/profile";
    }

    @GetMapping("subscribe/{user}")
    public String subscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user)
    {
        userService.subscribe(currentUser,user);

        return "redirect:/user-messages/" + user.getId();
    }
    @GetMapping("unsubscribe/{user}")
    public String unsubscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user)
    {
        userService.unsubscribe(currentUser,user);

        return "redirect:/user-messages/" + user.getId();
    }

    @GetMapping("{type}/{user}/list")
    public String userList(
            Model model,
            @PathVariable User user,
            @PathVariable String type
    ){
        model.addAttribute("userChannel", user);
        model.addAttribute("type",type);

        if(type.equals("subscriptions")){
            model.addAttribute("users",user.getSubscriptions());
        }
        else {
            model.addAttribute("users",user.getSubscribers());
        }

        return "subscriptions";
    }

}
