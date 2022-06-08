package com.melvinB.myWarung.service;

import com.melvinB.myWarung.dao.BelanjaRepository;
import com.melvinB.myWarung.dto.belanja.BelanjaHeaderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BelanjaService {

    @Autowired
    private BelanjaRepository belanjaRepository;

    public List<BelanjaHeaderDto> findAllBelanja() {
        return BelanjaHeaderDto.toList(belanjaRepository.findAll());
    }
}
