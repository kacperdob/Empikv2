import cart.Cart;
import shop.Product;
import shop.audio.Audio;
import shop.book.PaperBook;
import shop.game.Game;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

public class Adder {

    public Cart cartAdder (Cart cart) {
        Product audioAlbum = new Audio(
                1,
                "abc",
                new BigDecimal("100.00"),
                LocalDate.now(),
                Collections.EMPTY_LIST
        );

        Product audioAlbum2 = new Audio(
                2,
                "cba",
                new BigDecimal("543.00"),
                LocalDate.of(1992, 2, 24),
                Collections.emptyList()
        );

        Product book1 = new PaperBook(
                3,
                "pan Tadeusz",
                new BigDecimal("100.00"),
                "Adam",
                265
        );
        Product book2 = new PaperBook(
                3,
                "pan Tadeusz",
                new BigDecimal("100.00"),
                "Adam",
                665
        );
        Product book3 = new PaperBook(
                3,
                "pan Tadeusz",
                new BigDecimal("100.00"),
                "Adam",
                400
        );

        Product game1 = new Game(
                6,
                "diablo",
                new BigDecimal("40.00"),
                "moonlit"
        );
        Product game2 = new Game(
                6,
                "diablo2",
                new BigDecimal("50.00"),
                "moonlit"
        );
        Product game3 = new Game(
                6,
                "diablo3",
                new BigDecimal("60.00"),
                "sega"
        );
        Product game4 = new Game(
                6,
                "diablo4",
                new BigDecimal("70.00"),
                "moonlitqwerty"
        );

        cart.addProduct(audioAlbum);
        cart.addProduct(audioAlbum2);
        cart.addProduct(book1);
        cart.addProduct(book2);
        cart.addProduct(book3);
        cart.addProduct(game1);
        cart.addProduct(game2);
        cart.addProduct(game3);
        cart.addProduct(game4);
        return cart;
    }

}
