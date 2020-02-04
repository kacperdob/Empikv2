package cart;

import shop.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReceiptGenerator {
    public ReceiptGenerator(Cart cart) {
        this.listOfProducts = cart.getListOfProduct();
    }

    List<Product> listOfProducts;

    public List<String> recipeGenerator() {
        List<String> receipt = new ArrayList<>();
        receipt.add(ReceiptLine.header());
        for (Product product : listOfProducts) {
            ReceiptLine rec = new ReceiptLine(product,BigDecimal.valueOf(100));
            receipt.add(rec.receiptLine());
        }
        return receipt;
    }

}
