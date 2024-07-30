package fpt.edu.java5.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class HoaDonChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "IdHoaDon", nullable = false)
    private Integer idHoaDon;

    @NotNull
    @Column(name = "IdSPCT", nullable = false)
    private Integer idSPCT;

    @Min(1)
    @NotNull
    @Column(name = "SoLuong", nullable = false)
    private Integer soLuong;

    @Min(1)
    @NotNull
    @Column(name = "DonGia", nullable = false)
    private Double donGia;

    @NotNull
    @Column(name = "ThoiGian", nullable = false)
    private LocalDate thoiGian;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "TrangThai", nullable = false)
    private Integer trangThai;

}