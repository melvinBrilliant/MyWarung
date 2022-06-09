package com.melvinB.myWarung.service.kategori;

import com.melvinB.myWarung.dto.kategori.KategoriHeaderDto;
import com.melvinB.myWarung.dto.kategori.KategoriInsertDto;

import java.util.List;

public interface IKategoriService {
    List<KategoriHeaderDto> findAllKategori();
    Boolean kategoriExistsById(Integer id);
    KategoriHeaderDto findKategoriById(Integer kategoriID);
    KategoriInsertDto insertKategoriBaru(KategoriInsertDto insertKategori);
    KategoriHeaderDto updateKategori(Integer kategoriID, KategoriInsertDto updateKategori);
    String deleteKategori(Integer kategoriID);

}
