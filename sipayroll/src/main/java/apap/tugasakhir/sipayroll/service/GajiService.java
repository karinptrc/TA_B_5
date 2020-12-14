package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.GajiModel;
import apap.tugasakhir.sipayroll.model.UserModel;

import java.util.List;

public interface GajiService {
    void addGaji(GajiModel gaji);

    GajiModel getGajiById(Integer id);

    GajiModel updateGaji(GajiModel gaji);

    void deleteGaji(GajiModel gaji);

    List<GajiModel> getGajiList();

//    GajiModel updateStatusGaji(GajiModel gaji, Integer status);

    GajiModel setujuiGaji(GajiModel gaji, UserModel user);

    GajiModel tolakGaji(GajiModel gaji, UserModel user);
}
