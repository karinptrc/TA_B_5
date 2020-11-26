package apap.tugasakhir.sipayroll.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="user", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uuid;

    @NotNull
    @Size(max=50)
    @Column(name="username", nullable = false)
    private String username;

    @NotNull
    @Size(max=200)
    @Column(name="password", nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_gaji", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private GajiModel gaji;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_role", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RoleModel role;
}
