package fpt.edu.java5.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
public class KichThuoc {
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

    @NotNull
    @ColumnDefault("1")
    @Column(name = "TrangThai", nullable = false)
    private Integer trangThai;

}