package apap.tugasakhir.sipayroll.repository;

import apap.tugasakhir.sipayroll.model.GajiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GajiDb extends JpaRepository<GajiModel, Long> {
    List<GajiModel> findByTanggalMasukBefore(LocalDate time);
}
