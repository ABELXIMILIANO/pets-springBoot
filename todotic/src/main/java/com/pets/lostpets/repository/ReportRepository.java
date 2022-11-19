package com.pets.lostpets.repository;

import com.pets.lostpets.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportRepository extends JpaRepository<Report, Long> {

    Page<Report> findByNombreMascotaContaining(String nombreMascota, Pageable pageable);

    @Query(value = "SELECT *\n" +
            "FROM Report\n" +
            "ORDER BY Id DESC\n" +
            "LIMIT 8",nativeQuery = true)
    List<Report> OdenarReportes();
}
