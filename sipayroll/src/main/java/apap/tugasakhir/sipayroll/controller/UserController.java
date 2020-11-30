package apap.tugasakhir.sipayroll.controller;

import apap.tugasakhir.sipayroll.model.UserModel;
import apap.tugasakhir.sipayroll.service.RoleService;
import apap.tugasakhir.sipayroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @RequestMapping("/add")
    public String addUserPage(Model model){
        model.addAttribute("listRole", roleService.findAll());
        return "add-user";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUserSubmit(@ModelAttribute UserModel user, RedirectAttributes redir){
        userService.addUser(user);
        redir.addFlashAttribute("user", user);
        redir.addFlashAttribute("message", "Akun berhasil dibuat!");
        redir.addFlashAttribute("berhasil", true);
        return "redirect:/user/add";
    }

    @RequestMapping("/updatePassword")
    public String updateUserPage(Model model){
        model.addAttribute("listRole", roleService.findAll());
        return "ubah-password";
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public String changePassword(@RequestParam("oldpassword") String oldpassword,
                                 @RequestParam("password") String password,
                                 @RequestParam("confirmpassword") String confirmpassword, RedirectAttributes redir){
        if(!password.equals(confirmpassword)){
            redir.addFlashAttribute("hasMessage", true);
            redir.addFlashAttribute("message", "password baru dan konfirmasi password baru tidak cocok");
            return "redirect:/user/updatePassword";
        }
        UserModel user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if (!userService.checkIfValidOldPassword(user, oldpassword)) {
            redir.addFlashAttribute("hasMessage", true);
            redir.addFlashAttribute("message", "password lama invalid");
            return "redirect:/user/updatePassword";
        }

        if(!userService.checkIfValidNewPassword(password)){
            redir.addFlashAttribute("hasMessage", true);
            redir.addFlashAttribute("message", "password baru invalid. password harus terdiri dari minimal 8 karakter, serta mengandung huruf dan angka.");
            return "redirect:/user/updatePassword";
        }
        userService.changePassword(user, password);
        redir.addFlashAttribute("hasMessage", true);
        redir.addFlashAttribute("message", "password berhasil diganti");
        return "redirect:/user/updatePassword";
    }
}