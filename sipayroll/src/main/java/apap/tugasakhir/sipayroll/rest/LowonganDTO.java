package apap.tugasakhir.sipayroll.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(allowGetters = true)
public class LowonganDTO {
    @JsonProperty("divisi")
    private String divisi;

    @JsonProperty("posisi")
    private String posisi;

    @JsonProperty("jenisLowongan")
    private Long jenisLowongan;

    @JsonProperty("jumlahKaryawan")
    private Integer jumlahKaryawan;

    // @JsonProperty("uuid")
    // private String uuid;

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

    public Long getJenisLowongan() {
        return jenisLowongan;
    }

    public void setJenisLowongan(Long jenisLowongan) {
        this.jenisLowongan = jenisLowongan;
    }

    public Integer getJumlahKaryawan() {
        return jumlahKaryawan;
    }

    public void setJumlahKaryawan(Integer jumlahKaryawan) {
        this.jumlahKaryawan = jumlahKaryawan;
    }
}