package fpt.edu.java5.entites.custom;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HoaDonCustom {
    @Id
    private Integer id;
    private String tenKhachHang;
    private String tenNhanVien;
    private LocalDate ngayMuaHang;
    private Integer trangThai;

}