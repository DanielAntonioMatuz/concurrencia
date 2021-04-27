package sample.Model;

import java.util.ArrayList;
import java.util.Random;

public class dataBase {
    private ArrayList<String> buffer;
    private Random random;

    public dataBase() {
        buffer = new ArrayList<>();
        random = new Random(System.currentTimeMillis());
    }

    public void insert(String dato) {

        buffer.add(Thread.currentThread().getName());

    }

    public String read() {

        return buffer.get(random.nextInt(buffer.size()));

    }

    @Override
    public String toString() {
        return "dataBase{" +
                "buffer=" + buffer +
                '}';
    }
}
