package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.GajiModel;
import java.util.List;

public interface GajiService {
    void addGaji(GajiModel gaji);

    GajiModel getGajiById(Integer id);

    GajiModel updateGaji(GajiModel gaji);

    void deleteGaji(GajiModel gaji);

    List<GajiModel> getGajiList();

    GajiModel updateStatusGaji(Integer idGaji, Integer status);
}
