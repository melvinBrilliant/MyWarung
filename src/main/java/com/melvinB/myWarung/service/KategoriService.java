package com.melvinB.myWarung.service;

import com.melvinB.myWarung.dao.KategoriRepository;
import com.melvinB.myWarung.dto.kategori.KategoriHeaderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class KategoriService {

    @Autowired
    private KategoriRepository kategoriRepository;

    public List<KategoriHeaderDto> findAllKategori() {
        return KategoriHeaderDto.toList(kategoriRepository.findAll());
    }
}
