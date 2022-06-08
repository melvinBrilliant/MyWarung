package com.melvinB.myWarung.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Produk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProdukID", nullable = false)
    private Integer id;

    @Column(name = "NamaProduk", nullable = false, length = 50)
    private String namaProduk;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "KategoriID", nullable = false)
    private Kategori kategoriID;

    @Column(name = "HargaPerUnit", nullable = false, precision = 19, scale = 4)
    private BigDecimal hargaPerUnit;

    @Column(name = "JumlahStok", nullable = false)
    private Integer jumlahStok;

    @OneToMany(mappedBy = "produkID")
    private Set<DetailBelanja> detailBelanjas = new LinkedHashSet<>();

}