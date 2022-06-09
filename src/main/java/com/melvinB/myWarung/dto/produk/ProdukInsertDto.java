package com.melvinB.myWarung.dto.produk;

import com.melvinB.myWarung.model.Kategori;
import com.melvinB.myWarung.model.Produk;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ProdukInsertDto implements Serializable {
    private final String namaProduk;
    private final Integer kategoriID;
    private final Double hargaPerUnit;
    private final Integer jumlahStok;

    public Produk convert() {
        return Produk.builder()
                .namaProduk(namaProduk)
                .kategoriID(Kategori.builder().id(kategoriID).build())
                .hargaPerUnit(BigDecimal.valueOf(hargaPerUnit))
                .jumlahStok(jumlahStok)
                .build();
    }
}
