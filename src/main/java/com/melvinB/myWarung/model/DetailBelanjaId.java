package com.melvinB.myWarung.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class DetailBelanjaId implements Serializable {
    private static final long serialVersionUID = -6142275493566766124L;
    @Column(name = "BelanjaID", nullable = false)
    private Integer belanjaID;

    @Column(name = "ProdukID", nullable = false)
    private Integer produkID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DetailBelanjaId entity = (DetailBelanjaId) o;
        return Objects.equals(this.produkID, entity.produkID) &&
                Objects.equals(this.belanjaID, entity.belanjaID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produkID, belanjaID);
    }

}