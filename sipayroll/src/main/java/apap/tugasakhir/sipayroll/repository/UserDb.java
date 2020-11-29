package apap.tugasakhir.sipayroll.repository;

import apap.tugasakhir.sipayroll.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserDb extends JpaRepository<UserModel, String> {
    UserModel findByUsername(String username);
    List<UserModel> getUserModelsByGaji_TanggalMasukBefore(LocalDate time);
}
