package Lab5;

import javax.xml.bind.annotation.*;

@XmlType(name = "color")
@XmlEnum(String.class)
public enum Color {
    GREEN,
    BLUE,
    YELLOW,
    WHITE,
    BROWN;
}