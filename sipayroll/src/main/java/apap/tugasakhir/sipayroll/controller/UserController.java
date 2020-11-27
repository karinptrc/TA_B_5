package apap.tugasakhir.sipayroll.controller;

import apap.tugasakhir.sipayroll.model.UserModel;
import apap.tugasakhir.sipayroll.service.RoleService;
import apap.tugasakhir.sipayroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public String addUserSubmit(@ModelAttribute UserModel user, Model model) {
        userService.addUser(user);
        boolean berhasil = true;
        model.addAttribute("berhasil", berhasil);
        model.addAttribute("message", "Akun berhasil dibuat!");
        model.addAttribute("user", user);
        return "add-user";
    }
    @RequestMapping("/add-user")
    public String addUserPage(Model model){
        model.addAttribute("listRole", roleService.findAll());
        return "add-user";
    }

}