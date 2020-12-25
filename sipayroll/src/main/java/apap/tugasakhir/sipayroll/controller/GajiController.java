package apap.tugasakhir.sipayroll.controller;
import apap.tugasakhir.sipayroll.model.BonusModel;
import apap.tugasakhir.sipayroll.model.GajiModel;
import apap.tugasakhir.sipayroll.model.UserModel;
import apap.tugasakhir.sipayroll.rest.BaseResponseGaji;
import apap.tugasakhir.sipayroll.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.*;


@Controller
public class GajiController {
    @Autowired
    private UserService userService;

    @Autowired
    private GajiService gajiService;

    @Autowired
    private LemburService lemburService;

    @Autowired
    private BonusService bonusService;

    @Autowired
    private DetailGajiRestService detailGajiRestService;

    @GetMapping("/gaji/add")
    public String addGajiFormPage(Model model){
        List<UserModel> listUser = userService.getUserList();
        model.addAttribute("listUser", listUser);
        return "form-add-gaji";
    }
    @PostMapping("/gaji/add")
    public String addGajiSubmit(@ModelAttribute GajiModel gaji, RedirectAttributes redir){
        UserModel user_pengaju = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<GajiModel> listGaji = gajiService.getGajiList();
        for(int i = 0; i < listGaji.size(); i++){
            if(listGaji.get(i).getUser() == gaji.getUser()){
                System.out.print("masuk sini");
                boolean gagal = true;
                redir.addFlashAttribute("gagal",gagal);
                return "redirect:/gaji/add";
            }
        }
        gaji.setPengaju(user_pengaju);
        gaji.setStatusPersetujuan(0);
        gaji.setPenyetuju(null);
        boolean berhasil = true;
        gajiService.addGaji(gaji);
        redir.addFlashAttribute("gaji", gaji);
        redir.addFlashAttribute("berhasil",berhasil);
        return "redirect:/gaji/add";
    }
    @GetMapping(value = "/gaji/update/{id}")
    public String updateGajiFormPage(@PathVariable Integer id, Model model) {
        GajiModel gaji = gajiService.getGajiById(id);
        List<UserModel> listUser = userService.getUserList();
        model.addAttribute("gaji", gaji);
        model.addAttribute("listUser", listUser);
        return "form-update-gaji";
    }

    @PostMapping("/gaji/update")
    public String updateGajiFormSubmit(@ModelAttribute GajiModel gaji, RedirectAttributes redir) {
        GajiModel gajiUpdated = gajiService.updateGaji(gaji);
        boolean berhasil = true;
        redir.addFlashAttribute("gaji", gaji);
        redir.addFlashAttribute("berhasil", berhasil);
        return "redirect:/gaji/update/"+gaji.getId();
    }

    @RequestMapping("gaji/delete/{id}")
    public String deleteGaji(
            @PathVariable(value = "id", required = true) Integer id,
            Model model
    ){
        GajiModel gaji = gajiService.getGajiById(id);
        gajiService.deleteGaji(gaji);
        model.addAttribute("gaji",gaji);
        return "delete-gaji";
    }

    @RequestMapping("/gaji")
    public String listGaji(
            Model model
    ){
        UserModel user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        //if user's role is Karyawan
        if(user.getRole().getId()==6){
            GajiModel gaji = user.getGaji();
            model.addAttribute("gaji", gaji);
            model.addAttribute("karyawan", true);
            model.addAttribute("hasGaji",  gaji != null);
            model.addAttribute("month", LocalDate.now().getMonth());
            return "daftar-gaji";
        }

        //if user's role is Kepala Departemen HR or Staff Payroll
        List<GajiModel> listGaji = gajiService.getGajiList();
        Integer gajiTambahan = 0;
        //calculate total pendapatan
        for (GajiModel gaji:listGaji) {
            gajiTambahan += lemburService.totalLemburinMonthByGaji(gaji);
            gajiTambahan += bonusService.totalBonusinMonthByGaji(gaji);
            gajiTambahan += gaji.getGajiPokok();
            gaji.setTotalPendapatan(gajiTambahan);
            gajiTambahan = 0;
        }

        List<UserModel> listUser = userService.getUserList();

        model.addAttribute("listGaji", listGaji);
        model.addAttribute("listUser", listUser);
        model.addAttribute("karyawan", false);
        model.addAttribute("hasGaji",  listGaji.size()>0);
        model.addAttribute("month", LocalDate.now().getMonth());
        return "daftar-gaji";
    }

    @RequestMapping(value = "/gaji/setujui/{id}")
    public String setujuiGaji(
            @PathVariable(value = "id") Integer id,
            RedirectAttributes redir
    ){
        List<GajiModel> listGaji = gajiService.getGajiList();

        UserModel penyetuju = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        GajiModel gaji = gajiService.setujuiGaji(gajiService.getGajiById(id), penyetuju);

        redir.addFlashAttribute("gajiUpdated", gaji);
        redir.addFlashAttribute("changedStatus", true);
        redir.addFlashAttribute("statusGaji", true); // if gaji disetujui
        redir.addFlashAttribute("listGaji", listGaji);
        return "redirect:/gaji";
    }

    @RequestMapping(value = "/gaji/tolak/{id}")
    public String tolakGaji(
            @PathVariable(value = "id") Integer id,
            RedirectAttributes redir
    ){
        List<GajiModel> listGaji = gajiService.getGajiList();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserModel penyetuju = userService.findUserByUsername(username);
        GajiModel gaji = gajiService.tolakGaji(gajiService.getGajiById(id), penyetuju);

        redir.addFlashAttribute("gajiUpdated", gaji);
        redir.addFlashAttribute("changedStatus", true);
        redir.addFlashAttribute("statusGaji", false); // if gaji ditolak
        redir.addFlashAttribute("listGaji", listGaji);
        return "redirect:/gaji";
    }


    ///FITUR 8 -- ANDI
    @GetMapping(value = "/gaji/{id}/{username}")
    public String detailGaji(
            @PathVariable(value = "id") Integer id, @PathVariable(value = "username")String username, Model model){
        GajiModel gaji = gajiService.getGajiById(id);
        List<BonusModel> bonus = bonusService.getBonusByIdGaji(id);

        Integer totalLembur = lemburService.totalLemburinMonthByGaji(gaji);

        int totalBonus = 0;
        for (int i = 0; i<bonus.size(); i++){
            totalBonus += bonus.get(i).getJumlahBonus();
        }
        System.out.println(totalBonus);
        System.out.println("total Lembur " + totalLembur);

        boolean hasPelatihan = true;

        Mono<BaseResponseGaji> response = detailGajiRestService.getListPesertaPelatihan(username);
        BaseResponseGaji fix = response.block();

        List<LinkedHashMap<String, String>> peserta = (List<LinkedHashMap<String, String>>)fix.getResult();
        System.out.println(peserta.size());
        System.out.println(peserta);
        if(peserta.size() == 0){
            hasPelatihan = false;
        }else{
            hasPelatihan = true;
        }

//        List<LinkedHashMap<String, String>> tmp = (List<LinkedHashMap<String, String>>)fix.getResult();
//
//        List <PesertaDetail> listPeserta = new ArrayList<>();


//        for(LinkedHashMap<String, String> x :tmp){
//            Set<String> keys = x.keySet();
//            PesertaDetail pesertatmp = new PesertaDetail();
//            for(String k:keys){
//                if (k.equals("nama")){
////                    System.out.println(x.get(k));
//                    pesertatmp.setNama(x.get(k));
//                }
//            }
//            listPeserta.add(pesertatmp);
//        }
//        System.out.println(listPeserta.get(0).getNama());

        model.addAttribute("pengaju", gaji.getPengaju().getUsername());
        model.addAttribute("penyetuju", gaji.getPenyetuju().getUsername());
        model.addAttribute("totalLembur", totalLembur);
        model.addAttribute("gaji", gaji);
        model.addAttribute("hasPelatihan", hasPelatihan);
        model.addAttribute("totalBonus", totalBonus);
        model.addAttribute("dateTime", LocalDateTime.now());
        return "view-detail-gaji";
    }

}
