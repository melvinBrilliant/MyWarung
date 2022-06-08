package com.melvinB.myWarung.dto.produk;

import com.melvinB.myWarung.model.Produk;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProdukHeaderDto implements Serializable {
    private final String namaProduk;
    private final String NamaKategori;
    private final BigDecimal hargaPerUnit;
    private final Integer jumlahStok;

    public static ProdukHeaderDto set(Produk produk) {
        return new ProdukHeaderDto(
                produk.getNamaProduk(),
                produk.getKategoriID().getNamaKategori(),
                produk.getHargaPerUnit(),
                produk.getJumlahStok()
        );
    }

    public static List<ProdukHeaderDto> toList(List<Produk> produks) {
        List<ProdukHeaderDto> result = new ArrayList<>();

        produks.stream()
                .map(ProdukHeaderDto::set)
                .forEach(result::add);
        return result;
    }
}
