package apap.tugasakhir.sipayroll.model;

import javax.persistence.*;

@Entity
@Table(name="gaji")
public class GajiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
