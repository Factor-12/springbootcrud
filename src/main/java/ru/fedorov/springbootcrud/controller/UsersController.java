package ru.fedorov.springbootcrud.controller;

import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fedorov.springbootcrud.model.Role;
import ru.fedorov.springbootcrud.model.User;
import ru.fedorov.springbootcrud.service.RoleService;
import ru.fedorov.springbootcrud.service.UserService;

@Controller
@RequestMapping("/")
public class UsersController {

    private final UserService userService;
    private final RoleService roleService;

    public UsersController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("admin")
    public String listUser(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.getAll());
        return "admin";
    }

    @GetMapping("user")
    public String infoUser(@AuthenticationPrincipal User user, ModelMap model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user";
    }

    @GetMapping(value = "admin/user/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "newUser";
    }

    @PostMapping(value = "admin/user/new")
    public String newUser(@ModelAttribute User user,
            @RequestParam(value = "roless") String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add(roleService.getRole(role));
        }
        user.setRoles(roleSet);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/user/info/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "show";
    }

    @GetMapping(value = "admin/user/edit/{id}")
    public String editUser(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "editUser";
    }

    @PostMapping(value = "admin/user/edit/{id}")
    public String editUser(@ModelAttribute User user,
            @RequestParam(value = "roless") String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            roleSet.add((roleService.getRole(role)));
        }
        user.setRoles(roleSet);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/user/remove/{id}")
    public String removeUser(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/admin";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
}
