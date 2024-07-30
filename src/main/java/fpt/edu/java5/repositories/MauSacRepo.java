package fpt.edu.java5.repositories;

import fpt.edu.java5.entites.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MauSacRepo extends JpaRepository<MauSac, Integer> {

    public List<MauSac> findAllByTenContains(String name);


}
