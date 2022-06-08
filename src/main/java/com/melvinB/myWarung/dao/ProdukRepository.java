package com.melvinB.myWarung.dao;

import com.melvinB.myWarung.model.Produk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdukRepository extends JpaRepository<Produk, Integer> {
}