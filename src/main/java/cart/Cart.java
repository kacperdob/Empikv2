package cart;

import shop.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    final List<Product> listOfProduct;

    public List<Product> getListOfProduct() {
        return listOfProduct;
    }

    public Cart(List<Product> listOfProduct) {
        this.listOfProduct = listOfProduct;
    }

    public Cart() {
        listOfProduct = new ArrayList<>();
    }

    public void addProduct(Product product) {
        listOfProduct.add(product);
    }

    public void deleteProduct(Product product) {
        listOfProduct.remove(product);
    }

    public BigDecimal productsPrice() {
        return listOfProduct
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
   }
