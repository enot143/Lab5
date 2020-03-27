package Lab5;

import com.sun.istack.NotNull;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlType
public class Location implements Comparable<Location> {
    private long x;
    private int y;

    private Float z; //Поле не может быть null

    public Location() {
    }

    public Location(long x, int y,@NotNull Float z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public double getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Float getZ() {
        return z;
    }

    public void setZ(Float z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location that = (Location) o;
        return (x == that.x) && (y == that.y) && (z.equals(that.z));
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public int compareTo(Location o) {
        int result = Integer.compare(this.y, o.y);
        if (result == 0) {
            result = Long.compare(this.x, o.x);
        }
        if (result == 0) {
            result = this.z.compareTo(o.z);
        }
        return result;
    }
}