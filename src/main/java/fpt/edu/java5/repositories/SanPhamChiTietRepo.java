package fpt.edu.java5.repositories;

import fpt.edu.java5.entites.SanPhamChiTiet;
import fpt.edu.java5.entites.custom.SanPhamChiTietCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamChiTietRepo extends JpaRepository<SanPhamChiTiet, Integer> {


    @Query("select new SanPhamChiTietCustom(spct.id, sp.ten, ms.ten, kt.ten, spct.maSPCT, spct.soLuong, spct.donGia, spct.trangThai) " +
            "from SanPhamChiTiet spct join SanPham sp on spct.idSanPham = sp.id " +
            "join MauSac ms on ms.id = spct.idMauSac " +
            "join KichThuoc kt on kt.id = spct.idKichThuoc")
    List<SanPhamChiTietCustom> findAllByCustom();


    @Query("select new SanPhamChiTietCustom(spct.id, sp.ten, ms.ten, kt.ten, spct.maSPCT, spct.soLuong, spct.donGia, spct.trangThai) " +
            "from SanPhamChiTiet spct join SanPham sp on spct.idSanPham = sp.id " +
            "join MauSac ms on ms.id = spct.idMauSac " +
            "join KichThuoc kt on kt.id = spct.idKichThuoc " +
            "where spct.soLuong > 0 and spct.trangThai = 1")
    List<SanPhamChiTietCustom> findAllByCustomAndSPCT();

    @Query("select new SanPhamChiTietCustom(spct.id, sp.ten, ms.ten, kt.ten, spct.maSPCT, spct.soLuong, spct.donGia, spct.trangThai) " +
            "from SanPhamChiTiet spct join SanPham sp on spct.idSanPham = sp.id " +
            "join MauSac ms on ms.id = spct.idMauSac " +
            "join KichThuoc kt on kt.id = spct.idKichThuoc")
    Page<SanPhamChiTietCustom> findAllByCustomPhanTrang(Pageable p);



    @Query("select new SanPhamChiTietCustom(spct.id, sp.ten, ms.ten, kt.ten, spct.maSPCT, spct.soLuong, spct.donGia, spct.trangThai) " +
            "from SanPhamChiTiet spct join SanPham sp on spct.idSanPham = sp.id " +
            "join MauSac ms on ms.id = spct.idMauSac " +
            "join KichThuoc kt on kt.id = spct.idKichThuoc " +
            "where sp.id = :idSP")
    List<SanPhamChiTietCustom> findAllByIdSanPham(Integer idSP);
}
