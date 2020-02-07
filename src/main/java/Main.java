import cart.Cart;
import cart.ReceiptGenerator;


public class Main {
    public static void main(String[] args) {
        Cart cart = new Cart();

        Adder adder = new Adder();
        adder.cartAdder(cart);

        ReceiptGenerator receiptGen = new ReceiptGenerator(cart);
        for (String string : receiptGen.recipeGenerator()){
            System.out.println(string);
        }

    }


}
