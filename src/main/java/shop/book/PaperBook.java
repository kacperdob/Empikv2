package shop.book;

import lombok.Getter;

import java.math.BigDecimal;
@Getter
public class PaperBook extends Book{
    public PaperBook(long id, String name, BigDecimal price, String author, int pageCounter) {
        super(id, name, price, author, pageCounter);
    }
}
