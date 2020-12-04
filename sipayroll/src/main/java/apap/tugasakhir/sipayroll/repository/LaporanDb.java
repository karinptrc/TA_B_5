package apap.tugasakhir.sipayroll.repository;

import apap.tugasakhir.sipayroll.model.LaporanPesertaPelatihanModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaporanDb extends JpaRepository<LaporanPesertaPelatihanModel, Long> {
}
