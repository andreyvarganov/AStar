public class Canvas {

    /**
     * Класс-холст для отрисовки
     */

    // ширина и высота
    private int width;
    private int height;
    // матрица, на которой отрисовываем объекты
    private Sell[][] matrix;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new Sell[height][width];
    }

    /**
     * * Помещаем объект на карту: (x, y) - координаты, c - объект
     */

    void setPoint(Sell object) {

        int x = object.getX();
        int y = object.getY();

        if (y < 0 || y >= matrix.length) return;
        if (x < 0 || x >= matrix[0].length) return;

        matrix[y][x] = object;

    }

    /**
     * Вывод на экран
     */

    void print() {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(" ");
                System.out.print(matrix[i][j].getImage());
                System.out.print(" ");
            }
            System.out.println();
        }

    }

    public Sell[][] getMatrix() {
        return matrix;
    }

}

