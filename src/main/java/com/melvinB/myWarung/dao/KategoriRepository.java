package com.melvinB.myWarung.dao;

import com.melvinB.myWarung.model.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategoriRepository extends JpaRepository<Kategori, Integer> {
}