package com.melvinB.myWarung.service.kategori;

import com.melvinB.myWarung.dao.KategoriRepository;
import com.melvinB.myWarung.dto.kategori.KategoriHeaderDto;
import com.melvinB.myWarung.dto.kategori.KategoriInsertDto;
import com.melvinB.myWarung.model.Kategori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KategoriService implements IKategoriService {

    @Autowired
    private KategoriRepository kategoriRepository;

    @Override
    public List<KategoriHeaderDto> findAllKategori() {
        return KategoriHeaderDto.toList(kategoriRepository.findAll());
    }

    @Override
    public KategoriInsertDto insertKategoriBaru(KategoriInsertDto insertKategori) {
        Kategori kategoriBaru = insertKategori.convert();
        kategoriRepository.save(kategoriBaru);
        return insertKategori;
    }
}
