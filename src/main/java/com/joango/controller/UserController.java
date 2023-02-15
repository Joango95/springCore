package com.joango.controller;

import com.joango.facade.BookingFacade;
import com.joango.model.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("/{id}")
    public String getUserById(@PathVariable long id, Model model) {
            UserDTO user = bookingFacade.getUserById(id);
            model.addAttribute("users", user);
            return "users";
    }

    @GetMapping("/email/{email}")
    public String getUserByEmail(@PathVariable String email, Model model) {
        UserDTO user = bookingFacade.getUserByEmail(email);
        model.addAttribute("users", user);
        return "users";
    }

    @GetMapping("/name/{name}")
    public String getUsersByName(@PathVariable String name, Model model) {
        List<UserDTO> users = bookingFacade.getUsersByName(name);
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping()
    @ResponseBody
    public UserDTO createUser(@RequestBody UserDTO user) {
        return bookingFacade.createUser(user);
    }

    @PutMapping
    @ResponseBody
    public UserDTO updateUser(@RequestBody UserDTO user, Model model) {
        return bookingFacade.updateUser(user);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Boolean deleteUser(@PathVariable long userId, Model model) {
        bookingFacade.deleteUser(userId);
        return true;
    }
}

