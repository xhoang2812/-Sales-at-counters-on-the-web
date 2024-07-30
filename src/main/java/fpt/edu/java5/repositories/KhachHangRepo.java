package fpt.edu.java5.repositories;

import fpt.edu.java5.entites.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhachHangRepo extends JpaRepository<KhachHang, Integer> {

     List<KhachHang> findAllByTenContains(String name);

    KhachHang findBySdt(String sdt);
}
