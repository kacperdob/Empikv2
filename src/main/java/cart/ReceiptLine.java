package cart;

import shop.Product;

import java.math.BigDecimal;

public class ReceiptLine {

    private final long id;
    private final String name;
    private final BigDecimal price;
    private final BigDecimal priceAfterSale;

    public ReceiptLine(Product product, BigDecimal priceAfterSale) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.priceAfterSale = priceAfterSale;
    }

    public static final String formatLine = "[%3s] [%15s] [%6s] [%6s]";

    public static String header() {
        String header = String.format(formatLine, "id", "name", "price", "sale");
        return header;
    }

    public String receiptLine() {
        String recLine = String.format(formatLine, id, name, price, priceAfterSale);
        return recLine;
    }

}
