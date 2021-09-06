package ru.fedorov.springbootcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.fedorov.springbootcrud.model.User;
import ru.fedorov.springbootcrud.service.UserService;

@Controller
@RequestMapping("/")
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String allUser(Model model) {
        model.addAttribute("users", userService.getAll());
        return "all_user";
    }

    @GetMapping("/showNewUser")
    public String showNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "new_user";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/showUpdate/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "show";
    }

    @PostMapping("/showUpdate/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/";
    }
}
