package fpt.edu.java5.repositories;

import fpt.edu.java5.entites.KichThuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KichThuocRepo extends JpaRepository<KichThuoc, Integer> {

    List<KichThuoc> findAllByTenContains(String ten);
}
