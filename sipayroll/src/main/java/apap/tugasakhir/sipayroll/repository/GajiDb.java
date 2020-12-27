package apap.tugasakhir.sipayroll.repository;

import apap.tugasakhir.sipayroll.model.GajiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface GajiDb extends JpaRepository<GajiModel, Long> {
    Optional<GajiModel> findById(Integer id);

    Optional<GajiModel> findByUserUsername(String username);
}