package com.melvinB.myWarung.controller;

import com.melvinB.myWarung.dto.RestResponse;
import com.melvinB.myWarung.dto.kategori.KategoriHeaderDto;
import com.melvinB.myWarung.service.KategoriService;
import org.hibernate.hql.internal.ast.tree.ResolvableNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
