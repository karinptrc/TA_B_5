package apap.tugasakhir.sipayroll.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(allowGetters = true)
public class LowonganDTO {
    @JsonProperty("divisi")
    private String divisi;

    @JsonProperty("posisi")
    private String posisi;

    @JsonProperty("jenis_lowongan")
    private Integer jenis_lowongan;

    @JsonProperty("jumlah_karyawan")
    private Integer jumlahKaryawan;

    @JsonProperty("uuid")
    private String uuid;

    public String getDivisi() {
        return divisi;
    }

    public void setDivisi(String divisi) {
        this.divisi = divisi;
    }

    public String getPosisi() {
        return posisi;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public Integer getJenis_lowongan() {
        return jenis_lowongan;
    }

    public void setJenis_lowongan(Integer jenis_lowongan) {
        this.jenis_lowongan = jenis_lowongan;
    }

    public Integer getJumlahKaryawan() {
        return jumlahKaryawan;
    }

    public void setJumlahKaryawan(Integer jumlahKaryawan) {
        this.jumlahKaryawan = jumlahKaryawan;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}