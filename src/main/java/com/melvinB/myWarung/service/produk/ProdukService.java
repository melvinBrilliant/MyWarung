package com.melvinB.myWarung.service.produk;

import com.melvinB.myWarung.dao.KategoriRepository;
import com.melvinB.myWarung.dao.ProdukRepository;
import com.melvinB.myWarung.dto.produk.ProdukHeaderDto;
import com.melvinB.myWarung.dto.produk.ProdukInsertDto;
import com.melvinB.myWarung.model.Kategori;
import com.melvinB.myWarung.model.Produk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdukService implements IProductService {

    @Autowired
    private ProdukRepository produkRepository;

    @Autowired
    private KategoriRepository kategoriRepository;

    @Override
    public List<ProdukHeaderDto> findAllProduk() {
        return ProdukHeaderDto.toList(produkRepository.findAll());
    }

    @Override
    public Boolean kategoriByIdExists(Integer kategoriId) {
        return kategoriRepository.existsById(kategoriId);
    }

    @Override
    public ProdukInsertDto insertProdukBaru(ProdukInsertDto insertProduk) {
        Produk produkBaru = insertProduk.convert();
        produkRepository.save(produkBaru);
        return insertProduk;
    }

    @Override
    public ProdukHeaderDto updateProduk(Integer produkID, ProdukInsertDto updateProduk) {
        Produk produkLama = produkRepository.findById(produkID)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Produk dengan id tersebut tidak ditemukan"
                ));
        Kategori kategoriLama = produkLama.getKategoriID();
        Kategori kategoriBaru = kategoriRepository.findById(updateProduk.getKategoriID()).orElse(null);

        produkLama.setNamaProduk(updateProduk.getNamaProduk() == null?
                produkLama.getNamaProduk() : updateProduk.getNamaProduk());
        produkLama.setKategoriID(kategoriBaru == null?
                kategoriLama : kategoriBaru);
        produkLama.setHargaPerUnit(updateProduk.getHargaPerUnit() == null?
                produkLama.getHargaPerUnit() : BigDecimal.valueOf(updateProduk.getHargaPerUnit()));
        produkLama.setJumlahStok(updateProduk.getJumlahStok() == null?
                produkLama.getJumlahStok() : updateProduk.getJumlahStok());

        return ProdukHeaderDto.set(produkLama);
    }
}
