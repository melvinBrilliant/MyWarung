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
public class Belanja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BelanjaID", nullable = false)
    private Integer id;

    @Column(name = "Status", nullable = false, length = 50)
    private String status;

    @OneToMany(mappedBy = "belanjaID")
    private Set<DetailBelanja> detailBelanjas = new LinkedHashSet<>();

}