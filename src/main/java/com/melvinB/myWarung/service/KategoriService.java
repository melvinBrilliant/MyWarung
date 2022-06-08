package com.melvinB.myWarung.service;

import com.melvinB.myWarung.dao.KategoriRepository;
import com.melvinB.myWarung.dto.kategori.KategoriHeaderDto;
import com.melvinB.myWarung.dto.kategori.KategoriInsertDto;
import com.melvinB.myWarung.model.Kategori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategoriService {

    @Autowired
    private KategoriRepository kategoriRepository;

    public List<KategoriHeaderDto> findAllKategori() {
        return KategoriHeaderDto.toList(kategoriRepository.findAll());
    }

    public KategoriInsertDto insertKategoriBaru(KategoriInsertDto insertKategori) {
        Kategori kategoriBaru = insertKategori.convert();
        kategoriRepository.save(kategoriBaru);
        return insertKategori;
    }
}
