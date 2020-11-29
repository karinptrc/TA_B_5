package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.GajiModel;

import java.util.List;

public interface GajiRestService {
    List<GajiModel> getGajiFromKaryawanLama();
}
