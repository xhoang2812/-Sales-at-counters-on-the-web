package fpt.edu.java5.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "Ma")
    private String ma;

    @NotBlank
    @Size(max = 255)
    @Column(name = "Ten")
    private String ten;

    @NotBlank
    @Size(max = 255)
    @Column(name = "TenDangNhap")
    private String tenDangNhap;

    @NotBlank
    @Size(max = 255)
    @Column(name = "MatKhau")
    private String matKhau;

    @NotNull
    @Min(value = 0, message = "Trang thai co gia tri la 0 va 1")
    @Max(value = 1, message = "Trang thai co gia tri la 0 va 1")
    @Column(name = "TrangThai")
    private Integer trangThai;

}