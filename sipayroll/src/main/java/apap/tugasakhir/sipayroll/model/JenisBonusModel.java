package apap.tugasakhir.sipayroll.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="jenisbonus")
public class JenisBonusModel implements Serializable{
    @Id
    private Integer id;
}
