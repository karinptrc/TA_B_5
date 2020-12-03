package apap.tugasakhir.sipayroll.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="user")
public class UserModel implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    @Size(max=50)
    @Column(name="username", nullable = false)
    private String username;

    @NotNull
    @Lob
    @Column(name="password", nullable = false)
    @JsonIgnore
    private String password;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_gaji", referencedColumnName = "id")
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private GajiModel gaji;

    @OneToMany(mappedBy="penyetuju", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<GajiModel> listGajiDisetujui;

    @OneToMany(mappedBy="pengaju", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<GajiModel> listGajiDiajukan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_role", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RoleModel role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GajiModel getGaji() {
        return gaji;
    }

    public void setGaji(GajiModel gaji) {
        this.gaji = gaji;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    public List<GajiModel> getListGajiDisetujui() {
        return listGajiDisetujui;
    }

    public void setListGajiDisetujui(List<GajiModel> listGajiDisetujui) {
        this.listGajiDisetujui = listGajiDisetujui;
    }

    public List<GajiModel> getListGajiDiajukan() {
        return listGajiDiajukan;
    }

    public void setListGajiDiajukan(List<GajiModel> listGajiDiajukan) {
        this.listGajiDiajukan = listGajiDiajukan;
    }
}
