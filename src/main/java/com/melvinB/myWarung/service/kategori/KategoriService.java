package com.melvinB.myWarung.service.kategori;

import com.melvinB.myWarung.dao.KategoriRepository;
import com.melvinB.myWarung.dto.kategori.KategoriHeaderDto;
import com.melvinB.myWarung.dto.kategori.KategoriInsertDto;
import com.melvinB.myWarung.model.Kategori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public Boolean kategoriExistsById(Integer kategoriID) {
        return kategoriRepository.existsById(kategoriID);
    }

    @Override
    public KategoriHeaderDto findKategoriById(Integer kategoriID) {
        Kategori kategori = kategoriRepository.findById(kategoriID)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Kategori tidak ditemukan"));
        return KategoriHeaderDto.set(kategori);
    }

    @Override
    public KategoriInsertDto insertKategoriBaru(KategoriInsertDto insertKategori) {
        Kategori kategoriBaru = insertKategori.convert();
        kategoriRepository.save(kategoriBaru);
        return insertKategori;
    }

    @Override
    public KategoriHeaderDto updateKategori(Integer kategoriID, KategoriInsertDto updateKategori) {
        Kategori kategoriLama = kategoriRepository.findById(kategoriID)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Kategori dengan ID tersebut tidak ditemukan"
                ));

        kategoriLama.setNamaKategori(updateKategori.getNamaKategori() == null ?
                kategoriLama.getNamaKategori() : updateKategori.getNamaKategori());
        kategoriLama.setDeskripsi(updateKategori.getDeskripsi() == null ?
                kategoriLama.getDeskripsi() : updateKategori.getDeskripsi());
        kategoriRepository.save(kategoriLama);


        return KategoriHeaderDto.set(kategoriLama);
    }

    @Override
    public String deleteKategori(Integer kategoriID) {
        Kategori kategori = kategoriRepository.findById(kategoriID)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Kategori tidak ditemukan"
                ));
        try {
            String message = String.format("Berhasil menghapus kategori %s", kategori.getNamaKategori());
            kategoriRepository.deleteById(kategoriID);
            return message;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.METHOD_NOT_ALLOWED,
                    "Kategori tidak dapat dihapus karena masih terdapat produk di dalam kategori ini"
            );
        }
    }
}
