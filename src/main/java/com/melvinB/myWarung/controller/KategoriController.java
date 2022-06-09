package com.melvinB.myWarung.controller;

import com.melvinB.myWarung.dto.RestResponse;
import com.melvinB.myWarung.dto.kategori.KategoriHeaderDto;
import com.melvinB.myWarung.dto.kategori.KategoriInsertDto;
import com.melvinB.myWarung.service.kategori.KategoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("kategori")
public class KategoriController {

    @Autowired
    private KategoriService kategoriService;

    @GetMapping
    public ResponseEntity<RestResponse<List<KategoriHeaderDto>>> findAllKategori() {
        if (kategoriService.findAllKategori().size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(new RestResponse<>(
                        kategoriService.findAllKategori(),
                        "Belum ada kategori yang dimasukkan ke dalam database",
                        "204"
                ));
        } else {
            return ResponseEntity.ok()
                .body(new RestResponse<>(
                        kategoriService.findAllKategori(),
                        "Berhasil menampilkan semua kategori",
                        "200"
                ));
        }
    }

    @PostMapping("insert")
    public ResponseEntity<RestResponse<KategoriInsertDto>> insertKategoriBaru(
            @RequestBody KategoriInsertDto kategoriBaru
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RestResponse<>(
                        kategoriService.insertKategoriBaru(kategoriBaru),
                        "Berhasil memasukkan kategori baru",
                        "201"
                ));
    }

}
