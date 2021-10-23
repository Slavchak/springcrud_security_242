package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String findAll(ModelMap model){
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "all_users";
    }
    @GetMapping("/new-user")
    public String createUserForm (ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("all_role", roleService.allRoles());
        return "user-form-add";
    }

    @PostMapping("/new-user")
    public String saveUser (@ModelAttribute("user") User user,
                            @RequestParam(value = "role") String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        if (roles != null) {
            for (String roleName : roles) {
                roleSet.add(roleService.findRoleByName(roleName));
            }
        }
        user.setRoles(roleSet);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser (@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/user-edit/{id}")
    public String updateUserForm (@PathVariable("id") Long id, ModelMap modelMap) {
        User user = userService.getById(id);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("all_role", roleService.allRoles());
        return "user-form-update";
    }

    @PatchMapping("/user-edit/{id}")
    public String updateUser (@ModelAttribute("user") User user,
                              @RequestParam(value = "role") String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        if (roles != null) {
            for (String roleName : roles) {
                roleSet.add(roleService.findRoleByName(roleName));
            }
        }
        user.setRoles(roleSet);
        userService.update(user);
        return "redirect:/admin";
    }
}
