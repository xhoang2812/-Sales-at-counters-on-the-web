package fpt.edu.java5.entites.custom;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SanPhamChiTietCustom {
    @Id
    private Integer id;
    private String tenSP;
    private String tenMS;
    private String tenKT;
    private Integer idSanPham;
    private String maSPCT;
    private int soLuong;
    private double donGia;
    private int trangThai;

    public SanPhamChiTietCustom(Integer id, String tenSP, String tenMS, String tenKT, String maSPCT, int soLuong, double donGia, int trangThai) {
        this.id = id;
        this.tenSP = tenSP;
        this.tenMS = tenMS;
        this.tenKT = tenKT;
        this.maSPCT = maSPCT;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.trangThai = trangThai;
    }
}
