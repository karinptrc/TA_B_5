package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.GajiModel;
import apap.tugasakhir.sipayroll.model.UserModel;
import apap.tugasakhir.sipayroll.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class UserRestServiceImpl implements UserRestService{
    @Autowired
    UserDb userDb;

    @Override
    public List<UserModel> retrieveKaryawanLama() {
        LocalDate time = LocalDate.now().minusYears(2);
        return userDb.getUserModelsByGaji_TanggalMasukBefore(time);
    }
}
