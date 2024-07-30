package fpt.edu.java5.repositories;

import fpt.edu.java5.entites.HoaDon;
import fpt.edu.java5.entites.custom.HoaDonCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepo extends JpaRepository<HoaDon, Integer> {

    @Query("select new HoaDonCustom(hd.id,kh.ten,nv.ten, hd.ngayMuaHang, hd.trangThai)" +
            "from KhachHang kh join HoaDon hd on  kh.id = hd.idKH " +
            "join NhanVien nv on hd.idNV = nv.id ")
    List<HoaDonCustom> findAllByCustom();

    @Query("select new HoaDonCustom(hd.id,kh.ten,nv.ten, hd.ngayMuaHang, hd.trangThai)" +
            "from KhachHang kh join HoaDon hd on  kh.id = hd.idKH " +
            "join NhanVien nv on hd.idNV = nv.id ")
    Page<HoaDonCustom> findAllByCustomPhanTrang(Pageable pageable);

    @Query("select new HoaDonCustom(hd.id,kh.ten,nv.ten, hd.ngayMuaHang, hd.trangThai)" +
            "from KhachHang kh join HoaDon hd on  kh.id = hd.idKH " +
            "join NhanVien nv on hd.idNV = nv.id " +
            "where hd.trangThai != 1")
    List<HoaDonCustom> findAllByCustomByTrangThai();

    @Query("select new HoaDonCustom(hd.id,kh.ten,nv.ten, hd.ngayMuaHang, hd.trangThai)" +
            "from KhachHang kh join HoaDon hd on  kh.id = hd.idKH " +
            "join NhanVien nv on hd.idNV = nv.id " +
            "where hd.trangThai = 1")
    List<HoaDonCustom> findAllByCustomByTrangThai1();
}
