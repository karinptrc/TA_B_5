package apap.tugasakhir.sipayroll.repository;

import apap.tugasakhir.sipayroll.model.GajiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GajiDb extends JpaRepository<GajiModel, Long> {
    Optional<GajiModel> findById(Integer id);
    List<GajiModel> findByTanggalMasukBefore(LocalDate time);
}
