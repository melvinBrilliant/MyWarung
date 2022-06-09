package com.melvinB.myWarung.service.produk;

import com.melvinB.myWarung.dao.KategoriRepository;
import com.melvinB.myWarung.dao.ProdukRepository;
import com.melvinB.myWarung.dto.produk.ProdukHeaderDto;
import com.melvinB.myWarung.dto.produk.ProdukInsertDto;
import com.melvinB.myWarung.model.Produk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
