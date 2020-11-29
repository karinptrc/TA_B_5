package apap.tugasakhir.sipayroll.service;

import apap.tugasakhir.sipayroll.model.GajiModel;
import apap.tugasakhir.sipayroll.repository.GajiDb;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Service
@Transactional
public class GajiRestServiceImpl implements GajiRestService {
    @Autowired
    GajiDb gajiDb;

    @Override
    public List<GajiModel> getGajiFromKaryawanLama() {
        LocalDate time = LocalDate.now().minusYears(2);
        return gajiDb.findByTanggalMasukBefore(time);
    }
}
