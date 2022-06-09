package com.melvinB.myWarung.controller;

import com.melvinB.myWarung.dto.RestResponse;
import com.melvinB.myWarung.dto.produk.ProdukHeaderDto;
import com.melvinB.myWarung.dto.produk.ProdukInsertDto;
import com.melvinB.myWarung.service.produk.ProdukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("produk")
public class ProdukController {

    @Autowired
    private ProdukService produkService;

    @GetMapping
    public ResponseEntity<RestResponse<List<ProdukHeaderDto>>> findAllProduk() {
        if (produkService.findAllProduk().size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new RestResponse<>(
                            produkService.findAllProduk(),
                            "Belum ada produk yang dimasukkan ke database",
                            "204"
                    ));
        } else {
            return ResponseEntity.ok()
                    .body(new RestResponse<>(
                            produkService.findAllProduk(),
                            "Berhasil menampilkan semua produk",
                            "200"
                    ));
        }
    }

    @PostMapping("insert")
    public ResponseEntity<RestResponse<ProdukInsertDto>> insertProdukBaru(
            @RequestBody ProdukInsertDto produkBaru
    ) {
        if (produkService.kategoriByIdExists(produkBaru.getKategoriID())) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new RestResponse<>(
                            produkService.insertProdukBaru(produkBaru),
                            "Berhasil memasukkan produk baru",
                            "201"
                    ));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Gagal Memasukkan produk baru karena kategori tidak ditemukan");
        }
    }
}
