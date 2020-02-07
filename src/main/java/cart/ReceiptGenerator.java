package cart;

import shop.Product;
import shop.audio.Audio;
import shop.book.Book;
import shop.game.Game;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiptGenerator {
    public ReceiptGenerator(Cart cart) {
        this.listOfProducts = cart.getListOfProduct();
    }

    List<Product> listOfProducts;

    public List<String> recipeGenerator() {
        List<String> receipt = new ArrayList<>();
        receipt.add(ReceiptLine.header());
        gameListCreator(listOfProducts);
        for (Product product : listOfProducts) {
            ReceiptLine rec = new ReceiptLine(product, calculateDiscount(product));
            receipt.add(rec.receiptLine());
        }
        return receipt;
    }

//- AudioAlbum - od ceny pojedynczej płyty odejmujemy n%, gdzie n jest wiekiem płyty
//- Book - za każde 100 stron odejmuje się 10% od ceny, ale nie więcej niż 40%
//            - Poster - przy zakupie 3 sztuk, najtańszy jest za 1zł
//- Game - przy zakupie 3 gier (tego samego producenta), najtańsza gratis
//- Za każde wydane 100zł (licząc po uwzględnieniu rabatów) 5% zniżki

    public BigDecimal calculateDiscount(Product product) {
        BigDecimal priceAfterDiscount;

        if (product instanceof Audio) {
            priceAfterDiscount = audioAlbumDiscount((Audio) product);
            return priceAfterDiscount;

        } else if (product instanceof Book) {
            priceAfterDiscount = bookDiscount((Book) product);
            return priceAfterDiscount;
        }
//        else if (product instanceof Game) {
//            String firstPublischer = ((Game) product).getPublisher();
//
//        }

        return priceAfterDiscount = product.getPrice();
    }

    public BigDecimal audioAlbumDiscount(Audio audio) {
        LocalDate today = LocalDate.now();
        LocalDate albumDate = audio.getPublishmentDate();
        BigDecimal afterDiscount;
        BigDecimal audioDiscount;
        Period difference2 = Period.between(albumDate, today);
        BigDecimal yearsDifference = new BigDecimal(difference2.getYears());
        audioDiscount = audio.getPrice().multiply(yearsDifference);
        audioDiscount = audioDiscount.divide(BigDecimal.valueOf(100));
        afterDiscount = audio.getPrice().subtract(audioDiscount);
        return afterDiscount;
    }

    public BigDecimal bookDiscount(Book book) {
        BigDecimal priceAfterDiscount;
        int pagesDiscount = book.getPageCounter() / 100;
        BigDecimal discount = new BigDecimal(pagesDiscount);
        discount = discount.multiply(new BigDecimal(10));
        if (discount.compareTo(new BigDecimal(40)) > 0) {
            discount = new BigDecimal(40);
        }
        priceAfterDiscount = book.getPrice().subtract(discount);
        return priceAfterDiscount;
    }

    public BigDecimal gameDiscount(Game game) {
        BigDecimal priceAfterDiscount = game.getPrice();


        return priceAfterDiscount;
    }

    public void gameListCreator (List<Product> listOfProducts) {
        List<Game> listOfGames = new ArrayList<>();
        for (Product product : listOfProducts) {
            if (product instanceof Game) {
                listOfGames.add((Game) product);
            }
        }
        Map <String, List<Game>> discountControl = new HashMap<>();
        String publisher;
        List<Game> onePublisherGames = new ArrayList<>();
        for (int i = 0; i < listOfGames.size(); i++){
            publisher = listOfGames.get(i).getPublisher();
            if (discountControl.isEmpty()){
                onePublisherGames.add(listOfGames.get(i));
                discountControl.put(publisher, onePublisherGames);
            }
            else if (discountControl.containsKey(publisher)){
                List<Game> helpListOfGames = discountControl.get(publisher);
                helpListOfGames.add(listOfGames.get(i));
                discountControl.put(publisher, helpListOfGames);
            } else {
                List<Game> secondOnePublisherGame = new ArrayList<>();
                secondOnePublisherGame.add(listOfGames.get(i));
                discountControl.put(publisher, secondOnePublisherGame);
            }
        }
gamesMapCheck(discountControl);

    }

    public void gamesMapCheck (Map<String, List<Game>> mapToCheck){
        System.out.println("ilosc wydawców: " + mapToCheck.size());
        for (int i = 0; i < mapToCheck.size(); i++){
            System.out.println("gry wydawcy: ");

        }
    }
}
