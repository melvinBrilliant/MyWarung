package com.melvinB.myWarung.dto.belanja;

import com.melvinB.myWarung.model.Belanja;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertBelanjaDto {
    private String status;

    public Belanja convert() {
        return Belanja.builder().status(status).build();
    }
}
