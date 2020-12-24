package apap.tugasakhir.sipayroll.controller;

import apap.tugasakhir.sipayroll.model.RoleModel;
import apap.tugasakhir.sipayroll.model.UserModel;
import apap.tugasakhir.sipayroll.rest.BaseResponse;
import apap.tugasakhir.sipayroll.rest.PegawaiDTO;
import apap.tugasakhir.sipayroll.service.PegawaiRestAPIService;
import apap.tugasakhir.sipayroll.service.RoleService;
import apap.tugasakhir.sipayroll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PegawaiRestAPIService pegawaiRestAPIService;

    @RequestMapping("/add")
    public String addUserPage(Model model){
        model.addAttribute("pegawai", new PegawaiDTO());
        model.addAttribute("listRole", roleService.findAll());
        model.addAttribute("dateTime", LocalDateTime.now());
        return "add-user";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUserSubmit(@ModelAttribute PegawaiDTO pegawai,
                                @RequestParam("password") String password,
                                @RequestParam("tanggalLahir") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date tanggalLahir,
                                RedirectAttributes redir){
        if(userService.checkIfUsernameIsUsed(pegawai.getUsername())){
            redir.addFlashAttribute("hasMessage", true);
            redir.addFlashAttribute("message", "username " + pegawai.getUsername() + " sudah digunakan. silahkan ubah kembali username Anda.");
            return "redirect:/user/add";
        }
        if(!userService.checkIfValidNewPassword(password)){
            redir.addFlashAttribute("hasMessage", true);
            redir.addFlashAttribute("message", "password invalid. password harus terdiri dari minimal 8 karakter, serta mengandung huruf dan angka.");
            return "redirect:/user/add";
        }
        UserModel user = new UserModel();
        RoleModel role = roleService.findRoleById(pegawai.getRoleId());
        user.setUsername(pegawai.getUsername());
        user.setPassword(password);
        user.setRole(role);
        userService.addUser(user);
        pegawai.setTanggalLahir(tanggalLahir);
        pegawaiRestAPIService.addPegawai(pegawai);
        redir.addFlashAttribute("hasMessage", true);
        redir.addFlashAttribute("message", "user " + user.getUsername() + " berhasil ditambahkan");
        return "redirect:/user/add";
    }

    @RequestMapping("/updatePassword")
    public String updateUserPage(Model model){
        model.addAttribute("listRole", roleService.findAll());
        model.addAttribute("dateTime", LocalDateTime.now());
        return "ubah-password";
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public String changePassword(@RequestParam("oldpassword") String oldpassword,
                                 @RequestParam("password") String password,
                                 @RequestParam("confirmpassword") String confirmpassword,
                                 RedirectAttributes redir){

        UserModel user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if (!userService.checkIfValidOldPassword(user, oldpassword)) {
            redir.addFlashAttribute("hasMessage", true);
            redir.addFlashAttribute("message", "password lama invalid");
            return "redirect:/user/updatePassword";
        }

        if(oldpassword.equals(password)){
            redir.addFlashAttribute("hasMessage", true);
            redir.addFlashAttribute("message", "password baru tidak boleh sama dengan password lama");
            return "redirect:/user/updatePassword";
        }

        if(!password.equals(confirmpassword)){
            redir.addFlashAttribute("hasMessage", true);
            redir.addFlashAttribute("message", "password baru dan konfirmasi password baru tidak cocok");
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

    @RequestMapping(value = "/profil")
    private String profil(Model model) throws WebClientException{
        UserModel user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        RoleModel role = roleService.findRoleById(user.getRole().getId());
        try{
            BaseResponse baseResponse = pegawaiRestAPIService.getPegawai(user.getUsername());
            PegawaiDTO pegawai = baseResponse.getResult();
            model.addAttribute("isPegawai", true);
            model.addAttribute("pegawai", pegawai);
        }catch (WebClientException webClientException){ }
        model.addAttribute("username", user.getUsername());
        model.addAttribute("role", role.getRole());
        model.addAttribute("dateTime", LocalDateTime.now());
        return "profil-user";
    }
}