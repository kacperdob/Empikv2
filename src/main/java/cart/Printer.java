package cart;

import shop.Product;

import java.math.BigDecimal;

public class Printer {

    private final long id;
    private final String name;
    private final BigDecimal price;
    private final BigDecimal priceAfterSale;

    public Printer(Product product, BigDecimal priceAfterSale) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.priceAfterSale = priceAfterSale;
    }

    final String formatLine = "[%3s] [%15s] [%6s] [%6s]";

    public String header() {
        String header = String.format(formatLine, "id", "name", "price", "sale");
        return header;
    }

    public String recipeLine() {
        String recLine = String.format(formatLine, id, name, price, priceAfterSale);
        return recLine;
    }

}
