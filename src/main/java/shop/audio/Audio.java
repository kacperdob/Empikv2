package shop.audio;

import shop.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Audio extends Product {
    LocalDate publishmentDate;
    List<Track> trackList;

    public Audio(long id, String name, BigDecimal price, LocalDate publishmentDate, List<Track> trackList) {
        super(id, name, price);
        this.publishmentDate = publishmentDate;
        this.trackList = trackList;
    }
}
