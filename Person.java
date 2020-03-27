package Lab5;

import com.sun.istack.NotNull;
import javax.xml.bind.annotation.*;


@XmlType
public class Person implements Comparable<Person> {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Длина строки не должна быть больше 40, Поле не может быть null
    private Color eyeColor; //Поле не может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле может быть null
    public Person(){}
    public Person(String name, String passportID, Color eyeColor, Color hairColor, Country nationality, Location location) {
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.location = location;
        this.name = name;
        this.nationality = nationality;
        this.passportID = passportID;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }
    @XmlElement
    public String getName() {
        return name;
    }
    @XmlElement
    public Color getEyeColor() {
        return eyeColor;
    }
    public void setEyeColor(@NotNull Color eyeColor) {
        this.eyeColor = eyeColor;
    }
    @XmlElement
    public Color getHairColor() {
        return hairColor;
    }

    public void setHairColor(@NotNull Color hairColor) {
        this.hairColor = hairColor;
    }
    @XmlElement
    public Country getNationality() {
        return nationality;
    }

    public void setNationality(@NotNull Country nationality) {
        this.nationality = nationality;
    }
    @XmlElement
    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }
    @XmlElement
    public Location getLocation() {
        return location;
    }

    public void setLocation(@NotNull Location location) {
        this.location = location;
    }
    @Override
    public String toString(){
       return "Person{" +
                "passportID=" + passportID +
                ", name='" + name + '\'' +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                ", nationality=" + nationality.toString() +
                ", location=" + location.toString() +
                '}';
    }
    @Override
    public int compareTo(Person o) {
        int result = name.compareTo(o.name);
        if (result == 0) {
            result = eyeColor.compareTo(o.eyeColor);
        }
        if (result == 0) {
            result = hairColor.compareTo(o.hairColor);
        }
        if (result == 0) {
            result = nationality.compareTo(o.nationality);
        }
        if (result == 0) {
            result = location.compareTo(o.location);
        }
        if (result == 0) {
            result = passportID.compareTo(o.passportID);
        }
        return result;
    }
}