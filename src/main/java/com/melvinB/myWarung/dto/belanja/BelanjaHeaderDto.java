package com.melvinB.myWarung.dto.belanja;

import com.melvinB.myWarung.model.Belanja;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class BelanjaHeaderDto implements Serializable {
    private final Integer id;
    private final String status;

    public static BelanjaHeaderDto set(Belanja belanja) {
        return new BelanjaHeaderDto(
                belanja.getId(),
                belanja.getStatus()
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
