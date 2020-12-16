package apap.tugasakhir.sipayroll.service;

import java.util.Date;

import apap.tugasakhir.sipayroll.model.*;

import java.util.List;

public interface LemburService {
    void addLembur(LemburModel lembur);
    LemburModel getLemburById(Integer id);
    LemburModel updateLembur(LemburModel lembur);
    Boolean bandingTanggal(Date mulai, Date selesai);
    Boolean bandingJam(Date mulai, Date selesai);
    Integer totalLemburinMonthByGaji(GajiModel gaji);
}
