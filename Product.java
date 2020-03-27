
package Lab5;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlType(name = "product")
public class Product implements Comparable<Product> {
    private static int globalID = 0;
    @XmlAttribute
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double price; //Значение поля должно быть больше 0
    private String partNumber; //Длина строки не должна быть больше 98, Строка не может быть пустой, Поле может быть null
    private Double manufactureCost; //Поле не может быть null
    private UnitOfMeasure unitOfMeasure; //Поле может быть null
    private Person owner; //Поле не может быть null

    public Product() {
        globalID++;
        this.id = globalID;
        this.creationDate = LocalDate.now();
    }

    public Product(String name, Coordinates coordinates, double price, String partNumber, Double manufactureCost, UnitOfMeasure unitOfMeasure, Person owner) {
        globalID++;
        this.id = globalID;
        this.coordinates = coordinates;
        this.manufactureCost = manufactureCost;
        this.name = name;
        this.owner = owner;
        this.price = price;
        this.partNumber = partNumber;
        this.unitOfMeasure = unitOfMeasure;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public Double getManufactureCost() {
        return manufactureCost;
    }

    public void setManufactureCost(Double manufactureCost) {
        this.manufactureCost = manufactureCost;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    /**
     * Обновляет значение элемента.
     * @throws IOException если ошибка ввода аргумента
     */
    public void update() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите название продукта");
        String name = reader.readLine();
        while (name.trim().equals("") || name == null) {
            System.out.println("Название не должно быть пустым или содержать только пробелы. Повторите ввод.");
            name = reader.readLine();
        }
        this.setName(name);

        System.out.println("Введите координаты x и y через запятую");
        double x = 792;
        Float y = null;
        boolean f = false;
        while (y == null || x > 791) {
            try {
                if (f) System.out.println("Максимальное значение x = 792. Повторите ввод.");
                f = true;
                String[] coordinates = reader.readLine().split(",");
                x = Double.parseDouble(coordinates[0]);
                y = Float.parseFloat(coordinates[1]);
            } catch (Exception e) {
                System.out.println("Данные введены некорректно. Повторите ввод.");
                f = false;
            }
        }
        this.setCoordinates(new Coordinates(x, y));

        System.out.println("Введите цену");
        double price = 0;
        while (price <= 0) {
            try {
                price = Double.parseDouble(reader.readLine());
                if (price <= 0) {
                    System.out.println("Цена должна быть больше нуля. Повторите ввод");
                }
            } catch (NumberFormatException e) {
                System.out.println("Данные введены некорректно. Повторите ввод.");
            }
        }
        this.setPrice(price);

        System.out.println("Введите предприятие");
        String partNumber = reader.readLine();
        while (partNumber == null || partNumber.trim().equals("")) {
            System.out.println("Название предприятия не может быть пустым или содержать только пробелы. Повторите ввод.");
            partNumber = reader.readLine();
        }
        this.setPartNumber(partNumber);

        System.out.println("Введите цену поставщика");
        Double manufactureCost = null;
        while (manufactureCost == null) {
            try {
                manufactureCost = Double.parseDouble(reader.readLine());
            } catch (Exception e) {
                System.out.println("Данные введены некорректно. Повторите ввод.");
            }
        }
        this.setManufactureCost(manufactureCost);

        System.out.println("Введите единицу иземерения веса. (KILOGRAMS," +
                "METERS," +
                "CENTIMETERS," +
                "SQUARE_METERS," +
                "MILLILITERS;)");
        UnitOfMeasure uom = null;
        while (uom == null) {
            try {
                String str = reader.readLine();
                str = str.toUpperCase();
                uom = UnitOfMeasure.valueOf(str);
            } catch (IllegalArgumentException e) {
                System.out.println("Введите корректную единицу");
            }
        }
        this.setUnitOfMeasure(uom);

        System.out.println("Введите имя владельца");
        String personName = reader.readLine();
        while (personName == null || personName.trim().equals("")) {
            System.out.println("Имя не может быть пустым или содержать только пробелы. Повторите ввод.");
            personName = reader.readLine();
        }

        System.out.println("Введите пасспорт владельца");
        String passportID = reader.readLine();
        while (passportID == null || passportID.trim().equals("")) {
            System.out.println("Паспорт не может быть пустым или содержать только пробелы. Повторите ввод.");
            passportID = reader.readLine();
        }

        System.out.println("Введите цвет глаз. (GREEN," +
                "BLUE," +
                "YELLOW," +
                "WHITE," +
                "BROWN)");
        Color eyeColor = null;
        while (eyeColor == null) {
            try {
                String st = reader.readLine();
                st = st.toUpperCase();
                eyeColor = Color.valueOf(st);
            } catch (IllegalArgumentException e) {
                System.out.println("Введите корректные данные");
            }
        }

        System.out.println("Введите цвет волос. (GREEN," +
                "BLUE," +
                "YELLOW," +
                "WHITE," +
                "BROWN)");
        Color hairColor = null;
        while (hairColor == null) {
            try {
                String s = reader.readLine();
                s = s.toUpperCase();
                hairColor = Color.valueOf(s);
            } catch (IllegalArgumentException e) {
                System.out.println("Введите корректные данные");
            }
        }

        System.out.println("Введите страну. (RUSSIA," +
                "SPAIN," +
                "NORTH_KOREA," +
                "JAPAN)");
        Country nationality = null;
        while (nationality == null) {
            try {
                String st = reader.readLine();
                st = st.toUpperCase();
                nationality = Country.valueOf(st);
            } catch (IllegalArgumentException e) {
                System.out.println("Введите корректные данные");
            }
        }

        System.out.println("Введите координаты локации x, y, z через запятую");
        long x1 = 0;
        int y1 = 0;
        Float z1 = null;
        while (z1 == null) {
            try {
                String[] location = reader.readLine().split(",");
                x1 = Long.parseLong(location[0]);
                y1 = Integer.parseInt(location[1]);
                z1 = Float.parseFloat(location[1]);
            } catch (Exception e) {
                System.out.println("Данные введены некорректно. Повторите ввод.");
            }
        }
        Person person = new Person(personName, passportID, eyeColor, hairColor, nationality, new Location(x1, y1, z1));
        this.setOwner(person);
        System.out.println("Элемент обновлен.");
    }
    @Override
    public String toString(){
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates.toString() +
                ", creationDate=" + creationDate +
                ", owner=" + owner.toString() +
                ", price=" + price +
                ", partNumber=" + partNumber +
                ", unirOfMeasure= " + unitOfMeasure.toString() +
                '}';
    }
    @Override
    public int compareTo(Product o) {
        int result = this.name.compareTo(o.name);
        if (result == 0) {
            result = this.coordinates.compareTo(o.coordinates);
        }
        if (result == 0) {
            result = Double.compare(o.price, this.price);
        }
        if (result == 0) {
            result = this.partNumber.compareTo(o.partNumber);
        }
        if (result == 0) {
            result = this.manufactureCost.compareTo(o.manufactureCost);
        }
        if (result == 0) {
            result = this.unitOfMeasure.compareTo(o.unitOfMeasure);
        }
        if (result == 0) {
            result = this.owner.compareTo(o.owner);
        }
        return result;
    }
}