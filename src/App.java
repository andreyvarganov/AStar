import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {

    public static App app;
    // размерность поля
    private static int N;
    // массив объектов
    private static Canvas canvas;
    // массив зданий
    private static List<Building> buildings;
    // список складов
    private static List<Warehouse> warehouses;
    // список машин
    private static List<Car> cars;
    // маршрут
    private static List<Sell> road = new ArrayList<>();

    public App(int N) {
        App.N = N;
        canvas = new Canvas(N, N);
        buildings = new ArrayList<>();
        warehouses = new ArrayList<>();
        cars = new ArrayList<>();
    }

    public void draw() {
        drawBorders();
        setBaseObjects();
        // рисуем границы
        canvas.print();

    }

    public void setBaseObjects() {

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                canvas.setPoint(new EmptySell(i, j));
            }
        }

        // координаты здания
        int x, y;
        // требуемое количество зданий
        int quantity = (int) (N + Math.random() *  (0.75 * (N - 1) * (N - 1)));

        Building building;

        while (Building.getQuantity() < quantity) {
            do {
                x = (int) (1 + (Math.random() * (N - 1)));
                y = (int) (1 + (Math.random() * (N - 1)));
                building = new Building(x, y);
                if (canvas.getMatrix()[y][x].isAvailable) break;
            } while (true);
            canvas.setPoint(building);
            buildings.add(building);
        }

        // требуемое количество складов
//        quantity = (int) (2 + Math.random() * 5 );
        quantity = 1;
        Warehouse warehouse;

        while (warehouses.size() < quantity) {
            do {
                x = (int) (1 + Math.random() * (N - 1));
                y = (int) (1 + Math.random() * (N - 1));
                warehouse = new Warehouse(x, y);
                if (canvas.getMatrix()[y][x].isAvailable) break;
            } while (true);
            canvas.setPoint(warehouse);
            warehouses.add(warehouse);
        }

        // машина
        Car car;
        do {
            x = (int) (1 + Math.random() * (N - 1));
            y = (int) (1 + Math.random() * (N - 1));
            car = new Car(x, y);
            if (canvas.getMatrix()[y][x].isAvailable) break;
        } while (true);
        canvas.setPoint(car);
        cars.add(car);
    }

    public void drawBorders() {
        for (int i = 0; i < N; i++) {
            canvas.setPoint(new Boarder(i, 0));
            canvas.setPoint(new Boarder(i, N - 1));
        }

        for (int i = 0; i < N; i++) {
            canvas.setPoint(new Boarder(0, i));
            canvas.setPoint(new Boarder(N - 1, i));
        }
    }

    public void run() {
        // расставляем все объекты на карте;
        draw();
        // для всех объектов находим соседей
        road = Algorithm.searching(canvas, cars.get(0), warehouses.get(0));
        if (!road.isEmpty()) {
            Collections.reverse(road);
            System.out.println("Координаты: ");
            for (int i = 0; i < road.size(); i++) {
                System.out.print(road.get(i).getX() + "-" + road.get(i).getY());
                if (i != road.size()- 1) System.out.print(", ");
            }

            System.out.println("\nРезультат:");
            for (int i = 0; i < road.size() - 1; i++) {
                canvas.getMatrix()[road.get(i).y][road.get(i).x] = new Road(road.get(i).y, road.get(i).x);
            }
            canvas.print();
        }
        else System.out.println("Маршрут не существует!");
    }

    public static void main(String[] args) {
        // создаем карту размером NхN
        int N = 0;
        System.out.print("Введите N: ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        app = new App(N);
        app.run();
        System.out.println("\nКол-во зданий на карте: " + buildings.size());
        System.out.println("Кол-во складов на карте: " + warehouses.size());
    }
}
