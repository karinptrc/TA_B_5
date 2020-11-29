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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/add-user")
    public String addUserPage(Model model){
        model.addAttribute("listRole", roleService.findAll());
        return "add-user";
    }

    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public String addUserSubmit(@ModelAttribute UserModel user, RedirectAttributes redir){
        userService.addUser(user);
        redir.addFlashAttribute("user", user);
        redir.addFlashAttribute("message", "Akun berhasil dibuat!");
        redir.addFlashAttribute("berhasil", true);
        return "redirect:/user/add-user";
    }

}