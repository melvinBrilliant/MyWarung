package com.melvinB.myWarung.service;

import com.melvinB.myWarung.dao.BelanjaRepository;
import com.melvinB.myWarung.dao.DetailBelanjaRepository;
import com.melvinB.myWarung.dto.belanja.BelanjaHeaderDto;
import com.melvinB.myWarung.dto.detailBelanja.DetailBelanjaHeaderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BelanjaService {

    @Autowired
    private BelanjaRepository belanjaRepository;

    @Autowired
    private DetailBelanjaRepository detailBelanjaRepository;

    public List<BelanjaHeaderDto> findAllBelanja() {
        return BelanjaHeaderDto.toList(belanjaRepository.findAll());
    }

    public List<DetailBelanjaHeaderDto> findAllDetailBelanja() {
        if (detailBelanjaRepository.findAll().size() == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return DetailBelanjaHeaderDto.toList(detailBelanjaRepository.findAll());
    }
}
