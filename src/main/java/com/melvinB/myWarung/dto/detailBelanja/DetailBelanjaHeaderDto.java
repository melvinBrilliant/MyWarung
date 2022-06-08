package com.melvinB.myWarung.dto.detailBelanja;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class DetailBelanjaHeaderDto implements Serializable {
    private final Integer belanjaIDId;
    private final String belanjaIDStatus;
    private final String produkIDNamaProduk;
    private final Integer jumlahBarang;
    private final BigDecimal hargaPerUnit;


}
