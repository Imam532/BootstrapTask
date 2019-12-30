package bootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import bootstrap.model.Role;
import bootstrap.model.User;
import bootstrap.service.RoleService;
import bootstrap.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    @Autowired
    public UserService userService;
    @Autowired
    public RoleService roleService;



//    @Autowired
//    public void setUserService(UserService userService, RoleService userServiceRole) {
//        this.userService = userService;
//        this.roleService = userServiceRole;
//
//    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET )
    public ModelAndView listUsers(ModelAndView model) throws SQLException {
        List<User> listUsers = userService.getAllUsers();
        model.addObject("listUsers", listUsers);
        model.setViewName("HomePage");
        return model;
    }

//    @RequestMapping(value = "/admin/newUser", method = RequestMethod.GET)
//    public ModelAndView newUser(ModelAndView model) {
//        model.addObject("user", new User());
//        model.setViewName("AddUser");
//        return model;
//    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute User user, @RequestParam("roles") String role) throws SQLException {
        ModelAndView model = new ModelAndView();
        Set<Role> roles = new HashSet<>();
        if (userService.getUserByName(user.getName()) == null && role.equals("ADMIN,USER")) {
            String[] res = role.split(",");
            roles.add(roleService.getRole(res[0]));
            roles.add(roleService.getRole(res[1]));
            user.setRoles(roles);
            userService.addUser(user);
            model.setViewName("redirect:/admin/users");
        } else if (userService.getUserByName(user.getName()) == null){
            roles.add(roleService.getRole(role));
            user.setRoles(roles);
            userService.addUser(user);
            model.setViewName("redirect:/admin/users");
        }
        return model;
    }

    @GetMapping(value = "/admin/admin/delete/{id}")
    public ModelAndView deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/admin/users");
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.GET)
    public ModelAndView updatePage (HttpServletRequest request) throws SQLException {
        Long id = Long.parseLong(request.getParameter("id"));
        User user = userService.getUserById(id);
        ModelAndView model = new ModelAndView();
        model.setViewName("UpdateUser");
        model.addObject("user", user);
        return model;
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public ModelAndView updateUser (@ModelAttribute("user") User user,  @RequestParam("roles") String role) throws SQLException {
        ModelAndView model = new ModelAndView();
        Set<Role> roles = new HashSet<>();
        User user1 = userService.getUserById(user.getId());
        if (user1.getRoles().size() > 1) {
            user1.getRoles().clear();
            roles.add(roleService.getRole(role));
            user.setRoles(roles);
            userService.updateUser(user);
        } else {
            user1.getRoles().add(roleService.getRole(role));
            user.setRoles(user1.getRoles());
            userService.updateUser(user);
        }

        model.setViewName("redirect:/admin/users");
        return model;
    }
}
