package com.melvinB.myWarung.service.produk;

import com.melvinB.myWarung.dto.produk.ProdukHeaderDto;
import com.melvinB.myWarung.dto.produk.ProdukInsertDto;

import java.util.List;

public interface IProductService {
    List<ProdukHeaderDto> findAllProduk();
    Boolean kategoriByIdExists(Integer id);
    ProdukInsertDto insertProdukBaru(ProdukInsertDto insertProduk);
    ProdukHeaderDto updateProduk(Integer produkID, ProdukInsertDto updateProduk);
}
