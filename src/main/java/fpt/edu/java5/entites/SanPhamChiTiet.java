package fpt.edu.java5.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SanPhamChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "IdMauSac", nullable = false)
    private Integer idMauSac;

    @NotNull
    @Column(name = "IdKichThuoc", nullable = false)
    private Integer idKichThuoc;

    @NotNull
    @Column(name = "IdSanPham", nullable = false)
    private Integer idSanPham;

    @Size(max = 255)
    @NotBlank
    @Column(name = "MaSPCT", nullable = false)
    private String maSPCT;

    @NotNull
    @Min(0)
    @ColumnDefault("0")
    @Column(name = "SoLuong", nullable = false)
    private Integer soLuong;

    @NotNull
    @Min(0)
    @ColumnDefault("0")
    @Column(name = "DonGia", nullable = false)
    private Double donGia;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "TrangThai", nullable = false)
    private Integer trangThai;

}