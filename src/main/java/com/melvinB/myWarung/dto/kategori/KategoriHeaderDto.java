package com.melvinB.myWarung.dto.kategori;

import com.melvinB.myWarung.model.Kategori;
import lombok.*;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class KategoriHeaderDto implements Serializable {
    private final Integer id;
    private final String namaKategori;
    private final String deskripsi;
    private final int jumlahProduk;

    public static KategoriHeaderDto set(Kategori kategori) {
        return new KategoriHeaderDto(
                kategori.getId(),
                kategori.getNamaKategori(),
                kategori.getDeskripsi(),
                kategori.getProduks().size()
        );
    }

    public static List<KategoriHeaderDto> toList(List<Kategori> kategoris) {
        List<KategoriHeaderDto> result = new ArrayList<>();

        kategoris.stream()
                .map(KategoriHeaderDto::set)
                .forEach(result::add);

        return result;
    }
}
