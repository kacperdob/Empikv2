package shop.game;

import lombok.Getter;
import shop.Product;

import java.math.BigDecimal;
@Getter
public class Game extends Product {
    String publisher;

    public Game(long id, String name, BigDecimal price, String publisher) {
        super(id, name, price);
        this.publisher = publisher;
    }
}
