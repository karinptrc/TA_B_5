package apap.tugasakhir.sipayroll.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="gaji")
public class GajiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "gajiPokok", nullable = false)
    private Integer gajiPokok;

    @NotNull
    @Column(name = "statusPersetujuan")
    private Integer statusPersetujuan;

    @NotNull
    @Column(name = "tanggalMasuk")
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalMasuk;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_penyetuju", referencedColumnName = "id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel penyetuju;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pengaju", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UserModel pengaju;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false, unique = true)
    @JsonIgnore
    private UserModel user;

    @OneToMany(mappedBy = "gaji", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LemburModel> listLembur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(Integer gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    public Integer getStatusPersetujuan() {
        return statusPersetujuan;
    }

    public void setStatusPersetujuan(Integer statusPersetujuan) {
        this.statusPersetujuan = statusPersetujuan;
    }

    public LocalDate getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(LocalDate tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public UserModel getPenyetuju() {
        return penyetuju;
    }

    public void setPenyetuju(UserModel penyetuju) {
        this.penyetuju = penyetuju;
    }

    public UserModel getPengaju() {
        return pengaju;
    }

    public void setPengaju(UserModel pengaju) {
        this.pengaju = pengaju;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
