package apap.tugasakhir.sipayroll.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="bonus")
public class BonusModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name="jumlah_bonus", nullable = false)
    private Long jumlahBonus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_gaji", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GajiModel gaji;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_jenis_bonus", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private JenisBonusModel jenis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJumlahBonus() {
        return jumlahBonus;
    }

    public void setJumlahBonus(Long jumlahBonus) {
        this.jumlahBonus = jumlahBonus;
    }

    public GajiModel getGaji() {
        return gaji;
    }

    public void setGaji(GajiModel gaji) {
        this.gaji = gaji;
    }

    public JenisBonusModel getJenis() {
        return jenis;
    }

    public void setJenis(JenisBonusModel jenis) {
        this.jenis = jenis;
    }
}