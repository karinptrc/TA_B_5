package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.BonusModel;
import apap.tugasakhir.sipayroll.model.LaporanPesertaPelatihanModel;

public interface BonusRestService {
    BonusModel createLaporan(String username, Integer jumlah);
}
