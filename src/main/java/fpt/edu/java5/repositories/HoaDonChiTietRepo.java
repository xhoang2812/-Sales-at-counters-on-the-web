package fpt.edu.java5.repositories;

import fpt.edu.java5.entites.HoaDonChiTiet;
import fpt.edu.java5.entites.custom.HoaDonChiTietCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface HoaDonChiTietRepo extends JpaRepository<HoaDonChiTiet, Integer> {

    @Query("select new HoaDonChiTietCustom(hdct.id, hdct.idHoaDon, sp.ten, spct.id, hdct.soLuong, hdct.donGia, hdct.thoiGian, hdct.trangThai) " +
            "from HoaDonChiTiet hdct join SanPhamChiTiet spct on hdct.idSPCT = spct.id " +
            "join SanPham sp on spct.idSanPham = sp.id")
    List<HoaDonChiTietCustom> findAllByCustom();

    @Query("select new HoaDonChiTietCustom(hdct.id, hdct.idHoaDon, sp.ten, spct.id, hdct.soLuong, hdct.donGia, hdct.thoiGian, hdct.trangThai) " +
            "from HoaDonChiTiet hdct join SanPhamChiTiet spct on hdct.idSPCT = spct.id " +
            "join SanPham sp on spct.idSanPham = sp.id")
    Page<HoaDonChiTietCustom> findAllByCustomPhanTrang(Pageable pageable);

    @Query("select new HoaDonChiTietCustom(hdct.id, hd.id, sp.ten,spct.id, hdct.soLuong, hdct.donGia, hdct.thoiGian, hdct.trangThai) " +
            "from HoaDonChiTiet hdct join SanPhamChiTiet spct on hdct.idSPCT = spct.id " +
            "join SanPham sp on spct.idSanPham = sp.id " +
            "join HoaDon hd on hdct.idHoaDon = hd.id " +
            "where hd.id = :id")
    List<HoaDonChiTietCustom> findAllByCustomAndIdHoaDon(Integer id);

    void deleteByIdHoaDon(Integer id);

    List<HoaDonChiTiet> findAllByIdHoaDon(Integer id);

    HoaDonChiTiet findByIdHoaDonAndIdSPCT(Integer idHoaDon,Integer idSPCT);


}
