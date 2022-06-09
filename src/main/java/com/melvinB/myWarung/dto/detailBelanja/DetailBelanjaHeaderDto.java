package com.melvinB.myWarung.dto.detailBelanja;

import com.melvinB.myWarung.model.DetailBelanja;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class DetailBelanjaHeaderDto implements Serializable {
    private final Integer belanjaIDId;
    private final String produkIDNamaProduk;
    private final String belanjaIDStatus;
    private final Integer jumlahBarang;
    private final BigDecimal hargaPerUnit;

    public static DetailBelanjaHeaderDto set(DetailBelanja detailBelanja) {
        return new DetailBelanjaHeaderDto(
                detailBelanja.getBelanjaID().getId(),
                detailBelanja.getProdukID().getNamaProduk(),
                detailBelanja.getBelanjaID().getStatus(),
                detailBelanja.getJumlahBarang(),
                detailBelanja.getHargaPerUnit()
        );
    }

    public static List<DetailBelanjaHeaderDto> toList(List<DetailBelanja> detailBelanjaList) {
        List<DetailBelanjaHeaderDto> result = new ArrayList<>();

        detailBelanjaList.stream()
                .map(DetailBelanjaHeaderDto::set)
                .forEach(result::add);
        return result;
    }
}
