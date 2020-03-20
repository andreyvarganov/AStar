import java.util.List;

public abstract class Sell {
    // координаты объета
    protected int x, y;
    // доступность объекта
    protected boolean isAvailable;
    // отрисовка объекта
    protected String image;
    // соседние клетки
    protected List<Sell> neighbours;
    // родительская клетка
    protected Sell parent;
    // стоимость попадания в текущую клетку из родительской
    protected int G;
    // стоимость попадания из этой клетки в цель
    protected int H;
    // общая стоимость
    protected int F;
    /**
     * ↑ ↓ 🠔 🠖 🡔 🡕 🡖 🡗
     */

    public Sell(int x, int y, boolean isAvailable, String c) {
        this.x = x;
        this.y = y;
        this.isAvailable = isAvailable;
        this.image = c;
    }

    protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

    protected String getImage() {
        return image;
    }

    protected void setX(int x) {
        this.x = x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setNeighbours(List<Sell> neighbours) {
        this.neighbours = neighbours;
    }

    public void setParent(Sell parent) {
        this.parent = parent;
    }

    public void setG(int g) {
        G = g;
    }

    public void setH(int h) {
        H = h;
    }

    public void setF(int f) {
        F = f;
    }

    public List<Sell> getNeighbours() {
        return neighbours;
    }

    public Sell getParent() {
        return parent;
    }

    public int getG() {
        return G;
    }

    public int getH() {
        return H;
    }

    public int getF() {
        return F;
    }
}
