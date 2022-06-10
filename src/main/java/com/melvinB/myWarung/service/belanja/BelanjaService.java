package com.melvinB.myWarung.service.belanja;

import com.melvinB.myWarung.dao.BelanjaRepository;
import com.melvinB.myWarung.dao.DetailBelanjaRepository;
import com.melvinB.myWarung.dao.ProdukRepository;
import com.melvinB.myWarung.dto.belanja.BelanjaHeaderDto;
import com.melvinB.myWarung.dto.belanja.BeliDto;
import com.melvinB.myWarung.dto.belanja.InsertBelanjaDto;
import com.melvinB.myWarung.dto.detailBelanja.DetailBelanjaHeaderDto;
import com.melvinB.myWarung.model.Belanja;
import com.melvinB.myWarung.model.DetailBelanja;
import com.melvinB.myWarung.model.DetailBelanjaId;
import com.melvinB.myWarung.model.Produk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BelanjaService implements IBelanjaService{

    @Autowired
    private BelanjaRepository belanjaRepository;

    @Autowired
    private DetailBelanjaRepository detailBelanjaRepository;

    @Autowired
    private ProdukRepository produkRepository;

    @Override
    public List<BelanjaHeaderDto> findAllBelanja() {
        return BelanjaHeaderDto.toList(belanjaRepository.findAll());
    }

    @Override
    public List<DetailBelanjaHeaderDto> findAllDetailBelanja() {
        if (detailBelanjaRepository.findAll().size() == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return DetailBelanjaHeaderDto.toList(detailBelanjaRepository.findAll());
    }

    @Override
    public List<DetailBelanjaHeaderDto> beliProduk(List<BeliDto> keranjang) {
        InsertBelanjaDto insertBelanjaDto = new InsertBelanjaDto("Menunggu pembayaran / edit transaksi");
        Belanja belanjaBaru = insertBelanjaDto.convert();
        belanjaBaru.setStatus("Menunggu proses pembayaran / edit transaksi");
        belanjaRepository.save(belanjaBaru);
        Integer belanjaId = belanjaBaru.getId();

        for (BeliDto isiKeranjang : keranjang) {
            DetailBelanja detailBelanja = new DetailBelanja(
                    new DetailBelanjaId(belanjaId, isiKeranjang.getProdukID()),
                    belanjaRepository.findById(belanjaId).get(),
                    produkRepository.findById(isiKeranjang.getProdukID()).get(),
                    isiKeranjang.getJumlah(),
                    produkRepository.getHargaProduk(isiKeranjang.getProdukID())
            );
            detailBelanjaRepository.save(detailBelanja);
        }

        return DetailBelanjaHeaderDto.toList(detailBelanjaRepository.getDetailKeranjangById(belanjaId));
    }

    @Override
    public List<DetailBelanjaHeaderDto> konfirmasiBayar(Integer belanjaID) {
        Belanja belanja = belanjaRepository.findById(belanjaID)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "ID transaksi (belanja) tidak ditemukan"
                ));

        List<DetailBelanja> detailBelanjas = detailBelanjaRepository.getDetailKeranjangById(belanjaID);
        for (DetailBelanja isiDetail : detailBelanjas) {
            Produk produk = produkRepository.findById(isiDetail.getProdukID().getId()).get();
            Integer jumlahDibeli = isiDetail.getJumlahBarang();
            Integer jumlahStok = produk.getJumlahStok();

            if (jumlahDibeli > jumlahStok) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Jumlah barang dalam stok tidak mencukupi"
                );
            }

            produk.setJumlahStok(jumlahStok - jumlahDibeli);
        }
        belanja.setStatus("Transaksi selesai");
        return DetailBelanjaHeaderDto.toList(detailBelanjas);
    }

    @Override
    public List<DetailBelanjaHeaderDto> batalkanTransaksi(Integer belanjaID) {
        Belanja belanja = belanjaRepository.findById(belanjaID)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "ID transaksi (belanja) tidak ditemukan"
                ));
        List<DetailBelanja> detailBelanjas = detailBelanjaRepository.getDetailKeranjangById(belanjaID);

        belanja.setStatus("DIBATALKAN");
        return DetailBelanjaHeaderDto.toList(detailBelanjas);
    }

    @Override
    public List<DetailBelanjaHeaderDto> hapusBarangDalamTransaksi(Integer belanjaID, Integer produkID) {
        Belanja belanja = belanjaRepository.findById(belanjaID)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "ID transaksi (belanja) tidak ditemukan"
                ));
        detailBelanjaRepository.deleteById(new DetailBelanjaId(belanjaID, produkID));
        List<DetailBelanja> detailBelanjas = detailBelanjaRepository.getDetailKeranjangById(belanjaID);

        return DetailBelanjaHeaderDto.toList(detailBelanjas);
    }

    @Override
    public List<DetailBelanjaHeaderDto> tambahBarangDalamTransaksi(Integer belanjaID, BeliDto beliDto) {
        Belanja belanja = belanjaRepository.findById(belanjaID)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "ID transaksi (belanja) tidak ditemukan"
                ));

        DetailBelanja tambahBarang = new DetailBelanja(
                new DetailBelanjaId(belanjaID, beliDto.getProdukID()),
                belanjaRepository.findById(belanjaID).get(),
                produkRepository.findById(beliDto.getProdukID()).get(),
                beliDto.getJumlah(),
                produkRepository.getHargaProduk(beliDto.getProdukID())
        );
        detailBelanjaRepository.save(tambahBarang);
        List<DetailBelanja> detailBelanjas = detailBelanjaRepository.getDetailKeranjangById(belanjaID);

        return DetailBelanjaHeaderDto.toList(detailBelanjas);
    }
}
