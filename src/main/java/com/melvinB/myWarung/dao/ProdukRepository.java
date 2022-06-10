package com.melvinB.myWarung.dao;

import com.melvinB.myWarung.model.Produk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ProdukRepository extends JpaRepository<Produk, Integer> {
    @Query(value = """
            SELECT HargaPerUnit
            FROM Produk
            WHERE ProdukID = :produkID
            """, nativeQuery = true)
    BigDecimal getHargaProduk(Integer produkID);
}