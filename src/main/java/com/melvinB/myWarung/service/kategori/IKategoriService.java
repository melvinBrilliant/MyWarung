package com.melvinB.myWarung.service.kategori;

import com.melvinB.myWarung.dto.kategori.KategoriHeaderDto;
import com.melvinB.myWarung.dto.kategori.KategoriInsertDto;

import java.util.List;

public interface IKategoriService {
    List<KategoriHeaderDto> findAllKategori();
    KategoriInsertDto insertKategoriBaru(KategoriInsertDto insertKategori);

}
