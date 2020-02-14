package cart;

import shop.Product;
import shop.audio.Audio;
import shop.book.Book;
import shop.game.Game;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class ReceiptGenerator {
    public ReceiptGenerator(Cart cart) {
        this.listOfProducts = cart.getListOfProduct();
    }

    List<Product> listOfProducts;
    List<Game> discountGames = new ArrayList<>();

    public List<String> recipeGenerator() {
        List<String> receipt = new ArrayList<>();
        receipt.add(ReceiptLine.header());

        discountGames = gameDiscountList(
                mapSorter(
                        gameListCreator(listOfProducts)));

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
        } else if (product instanceof Game) {
            priceAfterDiscount = gameDiscount((Game) product);
            return priceAfterDiscount;
        }

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
        if (discountGames.contains(game)) {
            return BigDecimal.ZERO;
        }
        return priceAfterDiscount;
    }

    public Map<String, List<Game>> gameListCreator(List<Product> listOfProducts) {
        List<Game> listOfGames = new ArrayList<>();
        for (Product product : listOfProducts) {
            if (product instanceof Game) {
                listOfGames.add((Game) product);
            }
        }
        Map<String, List<Game>> discountControl = new HashMap<>();
        String publisher;
        List<Game> onePublisherGames = new ArrayList<>();
        for (int i = 0; i < listOfGames.size(); i++) {
            publisher = listOfGames.get(i).getPublisher();
            if (discountControl.isEmpty()) {
                onePublisherGames.add(listOfGames.get(i));
                discountControl.put(publisher, onePublisherGames);
            } else if (discountControl.containsKey(publisher)) {
                List<Game> helpListOfGames = discountControl.get(publisher);
                helpListOfGames.add(listOfGames.get(i));
                discountControl.put(publisher, helpListOfGames);
            } else {
                List<Game> secondOnePublisherGame = new ArrayList<>();
                secondOnePublisherGame.add(listOfGames.get(i));
                discountControl.put(publisher, secondOnePublisherGame);
            }
        }
        //      gamesMapCheck(discountControl);
        return discountControl;
    }

    public Map<String, List<Game>> mapSorter(Map<String, List<Game>> mapOfGames) {
        Map<String, List<Game>> sortedMap = new HashMap<>();
        for (String key : mapOfGames.keySet()) {
            List<Game> sortedList = mapOfGames.get(key).stream()
                    .sorted(Comparator.comparing(Game::getPrice))
                    .collect(Collectors.toList());
            sortedMap.put(key, sortedList);
        }
        return sortedMap;
    }

    public List<Game> gameDiscountList(Map<String, List<Game>> sortedMap) {
        List<Game> listOfDiscountedGames = new ArrayList<>();

        for (String key : sortedMap.keySet()) {
            listOfDiscountedGames = sortedMap.get(key)
                    .stream()
                    .limit(sortedMap.get(key).size() / 3L)
                    .collect(Collectors.toList());
        }
        return listOfDiscountedGames;
    }

    public void gamesMapCheck(Map<String, List<Game>> mapToCheck) {
        System.out.println("ilosc wydawców: " + mapToCheck.size());
        for (String key : mapToCheck.keySet()) {
            List<Game> onePublisherGames = mapToCheck.get(key);
            for (Game game : onePublisherGames) {
                System.out.println("gra " + key + " wydawcy to: " + game.getName());
            }
        }
        List<Game> listaKontrolna = mapToCheck.get("moonlit");
        List<Game> lista2;
        lista2 = listaKontrolna.stream()
                .sorted(Comparator.comparing(Game::getPrice))
                .collect(Collectors.toList());

        for (Game game : lista2) {
            System.out.println("gra: " + game.getName() + " koszuje " + game.getPrice());
        }
    }
}
