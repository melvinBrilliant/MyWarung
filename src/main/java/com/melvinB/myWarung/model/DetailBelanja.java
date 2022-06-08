package com.melvinB.myWarung.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class DetailBelanja {
    @EmbeddedId
    private DetailBelanjaId id;

    @MapsId("belanjaID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BelanjaID", nullable = false)
    private Belanja belanjaID;

    @MapsId("produkID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ProdukID", nullable = false)
    private Produk produkID;

    @Column(name = "JumlahBarang", nullable = false)
    private Integer jumlahBarang;

    @Column(name = "HargaPerUnit", nullable = false, precision = 19, scale = 4)
    private BigDecimal hargaPerUnit;

}