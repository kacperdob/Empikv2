import cart.Cart;
import cart.ReceiptGenerator;
import shop.Product;
import shop.audio.Audio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Cart cart = new Cart();

        Product audioAlbum = new Audio(
                1,
                "abc",
                new BigDecimal("10.0"),
                LocalDate.now(),
                Collections.EMPTY_LIST
        );

        Product audioAlbum2 = new Audio(
                2,
                "cba",
                new BigDecimal("10.0"),
                LocalDate.of(1992, 2, 24),
                Collections.emptyList()
        );
        cart.addProduct(audioAlbum);
        cart.addProduct(audioAlbum2);

        ReceiptGenerator receiptGen = new ReceiptGenerator(cart);
        for (String string : receiptGen.recipeGenerator()){
            System.out.println(string);
        }
    }


}
