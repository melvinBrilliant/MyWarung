package com.melvinB.myWarung.dao;

import com.melvinB.myWarung.model.DetailBelanja;
import com.melvinB.myWarung.model.DetailBelanjaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetailBelanjaRepository extends JpaRepository<DetailBelanja, DetailBelanjaId> {
    @Query(value = """
            SELECT *
            FROM DetailBelanja
            WHERE BelanjaID = :belanjaID
            """, nativeQuery = true)
    List<DetailBelanja> getDetailKeranjangById(Integer belanjaID);
}