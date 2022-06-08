package com.melvinB.myWarung.dto.kategori;

import com.melvinB.myWarung.model.Kategori;
import lombok.Data;

import java.io.Serializable;

@Data
public class KategoriInsertDto implements Serializable {
    private final String namaKategori;
    private final String deskripsi;

    public Kategori convert() {
        return Kategori.builder()
                .namaKategori(namaKategori)
                .deskripsi(deskripsi)
                .build();
    }
}
