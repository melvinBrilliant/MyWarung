package com.melvinB.myWarung.model;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Kategori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KategoriID", nullable = false)
    private Integer id;

    @Column(name = "NamaKategori", nullable = false, length = 50)
    private String namaKategori;

    @Column(name = "Deskripsi", length = 200)
    private String deskripsi;

    @OneToMany(mappedBy = "kategoriID")
    private Set<Produk> produks = new LinkedHashSet<>();

}