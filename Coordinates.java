package Lab5;

import com.sun.istack.NotNull;

import java.util.Objects;

public class Coordinates implements Comparable<Coordinates> {
    private double x; //Максимальное значение поля: 791
    private Float y; //Поле не может быть null

    public Coordinates(){}
    public Coordinates(double x, @NotNull Float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return (x == that.x) &&
                y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(@NotNull Float y) {
        this.y = y;
    }

    @Override
    public int compareTo(Coordinates o) {
        int result = Double.compare(this.x, o.x);
        if (result == 0) {
            result = this.y.compareTo(o.y);
        }
        return result;
    }
}