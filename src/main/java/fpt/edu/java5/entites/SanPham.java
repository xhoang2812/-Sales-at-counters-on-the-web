package fpt.edu.java5.entites;

import jakarta.persistence.*;
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
@Table(name = "SanPham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "Ma", nullable = false)
    private String ma;

    @Size(max = 255)
    @NotBlank
    @Column(name = "Ten", nullable = false)
    private String ten;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "TrangThai", nullable = false)
    private Integer trangThai;

}