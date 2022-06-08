package com.melvinB.myWarung.service;

import com.melvinB.myWarung.dao.ProdukRepository;
import com.melvinB.myWarung.dto.produk.ProdukHeaderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdukService {

    @Autowired
    private ProdukRepository produkRepository;

    public List<ProdukHeaderDto> findAllProduk() {
        return ProdukHeaderDto.toList(produkRepository.findAll());
    }
}
