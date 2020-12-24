package apap.tugasakhir.sipayroll.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@JsonIgnoreProperties(value={"roleName"},allowGetters = true)
public class PegawaiDTO {
    @NotNull
    @Size(max = 200)
    private String username;

    @NotNull
    @Size(max = 200)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 200)
    @Column(name = "no_telepon", nullable = false)
    private String noTelepon;

    @NotNull
    @Size(max = 200)
    private String tempatLahir;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
    private Date tanggalLahir;

    @NotNull
    @Size(max = 200)
    private String alamat;

    @NotNull
    private Integer roleId;

    private String roleName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getTempatLahir() { return tempatLahir; }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}