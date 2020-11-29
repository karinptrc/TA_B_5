package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.UserModel;

import java.time.LocalDate;
import java.util.List;

public interface UserRestService {
    List<UserModel> retrieveKaryawanLama();
}
