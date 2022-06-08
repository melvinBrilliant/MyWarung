package com.melvinB.myWarung.dto.belanja;

import com.melvinB.myWarung.model.Belanja;
import com.melvinB.myWarung.model.DetailBelanja;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class BelanjaHeaderDto implements Serializable {
    private final Integer id;
    private final String status;
    private final BigDecimal totalHarga;

    public static BelanjaHeaderDto set(Belanja belanja) {
        BigDecimal totalHarga = BigDecimal.valueOf(0);
        Set<DetailBelanja> detailBelanjas = belanja.getDetailBelanjas();
        detailBelanjas.stream()
                .map(detailBelanja -> {
                    BigDecimal hargaPerUnit = detailBelanja.getProdukID().getHargaPerUnit();
                    Integer jumlahBarangDiBeli = detailBelanja.getJumlahBarang();
                    return hargaPerUnit.multiply(BigDecimal.valueOf(jumlahBarangDiBeli));
                }).forEach(totalHarga::add);
        return new BelanjaHeaderDto(
                belanja.getId(),
                belanja.getStatus(),
                totalHarga
        );
    }

    public static List<BelanjaHeaderDto> toList(List<Belanja> belanjas) {
        List<BelanjaHeaderDto> result = new ArrayList<>();

        belanjas.stream()
                .map(BelanjaHeaderDto::set)
                .forEach(result::add);
        return result;
    }
}
