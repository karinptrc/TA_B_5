package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.GajiModel;

public interface GajiService {
    void addGaji(GajiModel gaji);

    GajiModel getGajiById(Integer id);

    GajiModel updateGaji(GajiModel gaji);

    void deleteGaji(GajiModel gaji);
}
