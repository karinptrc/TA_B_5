package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.rest.BaseResponse;
import apap.tugasakhir.sipayroll.rest.PegawaiDTO;

public interface PegawaiRestAPIService {
    BaseResponse addPegawai(PegawaiDTO pegawai);
    BaseResponse getPegawai(String username);
}
