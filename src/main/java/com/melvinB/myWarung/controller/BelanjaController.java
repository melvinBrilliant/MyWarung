package com.melvinB.myWarung.controller;

import com.melvinB.myWarung.dto.RestResponse;
import com.melvinB.myWarung.dto.belanja.BelanjaHeaderDto;
import com.melvinB.myWarung.dto.belanja.BeliDto;
import com.melvinB.myWarung.dto.detailBelanja.DetailBelanjaHeaderDto;
import com.melvinB.myWarung.service.belanja.BelanjaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("detail-all")
    public ResponseEntity<RestResponse<List<DetailBelanjaHeaderDto>>> findAllDetailBelanja() {
        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        belanjaService.findAllDetailBelanja(),
                        "Berhasil menampilkan semua detail belanja",
                        "200"
                ));
    }

    @PostMapping("beli")
    public ResponseEntity<RestResponse<List<DetailBelanjaHeaderDto>>> beliProduk(
            @RequestBody List<BeliDto> keranjang
    ) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new RestResponse<>(
                        belanjaService.beliProduk(keranjang),
                        "Barang-barang diterima, menunggu konfirmasi pembayaran",
                        "202"
                ));
    }

    @PutMapping("konfirmasi")
    public ResponseEntity<RestResponse<List<DetailBelanjaHeaderDto>>> beliProduk(
            @RequestParam Integer belanjaID
    ) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new RestResponse<>(
                        belanjaService.konfirmasiBayar(belanjaID),
                        "Transaksi Selesai",
                        "202"
                ));
    }

    @PutMapping("batalkan")
    public ResponseEntity<RestResponse<List<DetailBelanjaHeaderDto>>> batalkanTransaksi(
            @RequestParam Integer belanjaID) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new RestResponse<>(
                        belanjaService.batalkanTransaksi(belanjaID),
                        "Transaksi Dibatalkan",
                        "202"
                ));
    }

    @PutMapping("edit/tambah")
    public ResponseEntity<RestResponse<List<DetailBelanjaHeaderDto>>> tambahBarang(
            @RequestParam Integer belanjaID, @RequestBody BeliDto beliDto
    ) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new RestResponse<>(
                        belanjaService.tambahBarangDalamTransaksi(belanjaID, beliDto),
                        "Barang ditambahkan",
                        "202"
                ));
    }

    @DeleteMapping("edit/hapus")
    public ResponseEntity<RestResponse<List<DetailBelanjaHeaderDto>>> hapusBarang(
            @RequestParam Integer belanjaID, @RequestParam Integer produkID
    ) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new RestResponse<>(
                        belanjaService.hapusBarangDalamTransaksi(belanjaID, produkID),
                        "Barang berhasil dihapus",
                        "202"
                ));
    }
}
