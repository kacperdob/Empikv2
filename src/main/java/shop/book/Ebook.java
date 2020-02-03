package shop.book;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class Ebook extends Book {
    List<String> supportedFormats;

    public Ebook(long id, String name, BigDecimal price, String author, int pageCounter, List<String> supportedFormats) {
        super(id, name, price, author, pageCounter);
        this.supportedFormats = supportedFormats;
    }
}
