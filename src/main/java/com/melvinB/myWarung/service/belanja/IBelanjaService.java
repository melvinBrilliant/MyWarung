package com.melvinB.myWarung.service.belanja;

import com.melvinB.myWarung.dto.belanja.BelanjaHeaderDto;
import com.melvinB.myWarung.dto.belanja.BeliDto;
import com.melvinB.myWarung.dto.detailBelanja.DetailBelanjaHeaderDto;

import java.util.List;

public interface IBelanjaService {
    List<BelanjaHeaderDto> findAllBelanja();
    List<DetailBelanjaHeaderDto> findAllDetailBelanja();
    List<DetailBelanjaHeaderDto> beliProduk(List<BeliDto> keranjang);
    List<DetailBelanjaHeaderDto> konfirmasiBayar(Integer belanjaID);
    List<DetailBelanjaHeaderDto> batalkanTransaksi(Integer belanjaID);
    List<DetailBelanjaHeaderDto> hapusBarangDalamTransaksi(Integer belanjaID, Integer produkID);
    List<DetailBelanjaHeaderDto> tambahBarangDalamTransaksi(Integer belanjaID, BeliDto beliDto);
}
