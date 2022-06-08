package com.melvinB.myWarung.controller;

import com.melvinB.myWarung.dto.RestResponse;
import com.melvinB.myWarung.dto.belanja.BelanjaHeaderDto;
import com.melvinB.myWarung.service.BelanjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("belanja")
public class BelanjaController {

    @Autowired
    private BelanjaService belanjaService;

    @GetMapping
    public ResponseEntity<RestResponse<List<BelanjaHeaderDto>>> findAllBelanja() {
        if (belanjaService.findAllBelanja().size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body(new RestResponse<>(
                            belanjaService.findAllBelanja(),
                            "Belum ada transaksi belanja di dalam database",
                            "204"
                    ));
        } else {
            return ResponseEntity.ok()
                    .body(new RestResponse<>(
                            belanjaService.findAllBelanja(),
                            "Berhasil menampilkan semua transaksi belanja",
                            "200"
                    ));
        }
    }
}
