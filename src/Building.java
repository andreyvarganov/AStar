public class Building extends Sell {

    // кол-во зданий
    private static int quantity;

    public Building(int x, int y) {
        super(x, y, false, "#");
        quantity++;
    }

    public static int getQuantity() {
        return quantity;
    }

}
