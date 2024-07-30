package fpt.edu.java5.repositories;

import fpt.edu.java5.entites.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepo extends JpaRepository<NhanVien, Integer> {

    public List<NhanVien> findAllByTenContains(String name);

    public NhanVien findAllByTenDangNhap( String userName);
}
