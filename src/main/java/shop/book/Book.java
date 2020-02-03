package shop.book;

import lombok.Getter;
import shop.Product;

import java.math.BigDecimal;
@Getter
public abstract class Book extends Product {
    String author;
    int pageCounter;

    public Book(long id, String name, BigDecimal price, String author, int pageCounter) {
        super(id, name, price);
        this.author = author;
        this.pageCounter = pageCounter;
    }
}
