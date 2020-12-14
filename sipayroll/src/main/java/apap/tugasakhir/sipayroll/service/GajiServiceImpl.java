package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.GajiModel;
import apap.tugasakhir.sipayroll.model.UserModel;
import apap.tugasakhir.sipayroll.repository.GajiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GajiServiceImpl implements GajiService{
    @Autowired
    GajiDb gajiDb;

    @Autowired
    private UserService userService;

    @Override
    public void addGaji(GajiModel gaji) {
        try{
            gajiDb.save(gaji);
        }catch(Exception e){
            System.out.println("gagal");
        }
    }

    @Override
    public GajiModel getGajiById(Integer id) {
        return gajiDb.findById(id).get();
    }

    @Override
    public GajiModel updateGaji(GajiModel gaji) {
        GajiModel targetGaji = gajiDb.findById(gaji.getId()).get();
        UserModel user_pengaju = userService.findUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName());
        try{
            targetGaji.setGajiPokok(gaji.getGajiPokok());
            targetGaji.setStatusPersetujuan(0);
            targetGaji.setPengaju(user_pengaju);
            targetGaji.setPenyetuju(null);
            gajiDb.save(targetGaji);
            return targetGaji;
        }catch (NullPointerException nullPointerException){
            return null;
        }
    }

    @Override
    public void deleteGaji(GajiModel gaji) {
        gajiDb.delete(gaji);
        System.out.println("hapus");
    }

    @Override
    public List<GajiModel> getGajiList() {
        return gajiDb.findAll();
    }

//    @Override
//    public GajiModel updateStatusGaji(GajiModel gajiModel, Integer status) {
//        GajiModel gaji = gajiDb.findById(gajiModel.getId()).get();
//        if (status == 1){
//            //jika gaji ditolak
//            gaji.setStatusPersetujuan(1);
//            gaji.setPenyetuju();
//        } else {
//            //jika gaji disetujui
//            gaji.setStatusPersetujuan(2);
//        }
//        return gajiDb.save(gaji);
//    }


    @Override
    public GajiModel setujuiGaji(GajiModel gaji, UserModel user) {
        if (gaji.getStatusPersetujuan() == 0){
            gaji.setStatusPersetujuan(2);
            gaji.setPenyetuju(user);
        }
        return gajiDb.save(gaji);
    }

    @Override
    public GajiModel tolakGaji(GajiModel gaji, UserModel user) {
        if (gaji.getStatusPersetujuan() == 0){
            gaji.setStatusPersetujuan(1);
            gaji.setPenyetuju(user);
        }
        return gajiDb.save(gaji);
    }

    //    @Override
//    public boolean checkListIdUser(String id){
//        List<GajiModel> listIdUser =gajiDb.findAllByUserId(id);
//        listIdUser.con
//    }


}
