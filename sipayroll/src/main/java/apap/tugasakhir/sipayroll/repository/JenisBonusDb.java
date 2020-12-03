package apap.tugasakhir.sipayroll.repository;

import apap.tugasakhir.sipayroll.model.JenisBonusModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JenisBonusDb extends JpaRepository<JenisBonusModel, Long> {
    JenisBonusModel findById(Integer id);
}
