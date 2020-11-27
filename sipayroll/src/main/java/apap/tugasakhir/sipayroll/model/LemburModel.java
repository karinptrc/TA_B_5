package apap.tugasakhir.sipayroll.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="lembur")
public class LemburModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name="waktuMulai", nullable = false)
    private Date waktuMulai;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name="waktuSelesai", nullable = false)
    private Date waktuSelesai;

    @NotNull
    @Column(name="kompensasiPerJam", nullable = false)
    private Integer kompensasiPerJam;

    @NotNull
    @Column(name="statusPersetujuan", nullable = false)
    private Integer statusPersetujuan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_gaji", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GajiModel gaji;


}
