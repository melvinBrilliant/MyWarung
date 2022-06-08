package com.melvinB.myWarung.dao;

import com.melvinB.myWarung.model.DetailBelanja;
import com.melvinB.myWarung.model.DetailBelanjaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailBelanjaRepository extends JpaRepository<DetailBelanja, DetailBelanjaId> {
}