package fpt.edu.java5.entites.custom;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HoaDonChiTietCustom {
    @Id
    private Integer id;
    private Integer idHoaDon;
    private String tenSP;
    private Integer idSP;
    private Integer soLuong;
    private Double donGia;
    private LocalDate thoiGian;
    private Integer trangThai;
}
