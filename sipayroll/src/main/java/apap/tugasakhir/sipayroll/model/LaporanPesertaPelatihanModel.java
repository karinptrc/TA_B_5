package apap.tugasakhir.sipayroll.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "laporan_peserta_pelatihan")
public class LaporanPesertaPelatihanModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name="id_pelatihan", nullable = false)
    private Integer id_pelatihan;

    @NotNull
    @Size(max=50)
    @Column(name="no_peserta", nullable = false)
    private String no_peserta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_pelatihan() {
        return id_pelatihan;
    }

    public void setId_pelatihan(Integer id_pelatihan) {
        this.id_pelatihan = id_pelatihan;
    }

    public String getNo_peserta() {
        return no_peserta;
    }

    public void setNo_peserta(String no_peserta) {
        this.no_peserta = no_peserta;
    }
}
