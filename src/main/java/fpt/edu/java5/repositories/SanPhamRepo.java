package fpt.edu.java5.repositories;

import fpt.edu.java5.entites.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamRepo extends JpaRepository<SanPham, Integer> {

    List<SanPham> findAllByTenContains(String name);
}
