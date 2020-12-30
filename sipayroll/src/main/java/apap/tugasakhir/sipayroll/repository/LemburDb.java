package apap.tugasakhir.sipayroll.repository;

import apap.tugasakhir.sipayroll.model.GajiModel;
import apap.tugasakhir.sipayroll.model.LemburModel;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LemburDb extends JpaRepository<LemburModel, Integer>  {
    Optional<LemburModel> findById(Long id);
    List<LemburModel> getLemburModelsByGaji(GajiModel gaji);
}
