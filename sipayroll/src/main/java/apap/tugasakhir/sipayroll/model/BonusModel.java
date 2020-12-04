package apap.tugasakhir.sipayroll.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="bonus")
public class BonusModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name="jumlah_bonus", nullable = false)
    private Integer jumlahBonus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_gaji", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GajiModel gaji;

    @NotNull
    @Column(name = "tanggal_diberikan")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonIgnore
    private LocalDate tanggal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_jenis_bonus", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JenisBonusModel jenis;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJumlahBonus() {
        return jumlahBonus;
    }

    public void setJumlahBonus(Integer jumlahBonus) {
        this.jumlahBonus = jumlahBonus;
    }

    public GajiModel getGaji() {
        return gaji;
    }

    public void setGaji(GajiModel gaji) {
        this.gaji = gaji;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public JenisBonusModel getJenis() {
        return jenis;
    }

    public void setJenis(JenisBonusModel jenis) {
        this.jenis = jenis;
    }
}