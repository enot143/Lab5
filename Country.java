package Lab5;
import javax.xml.bind.annotation.*;

@XmlType(name = "country")
@XmlEnum(String.class)
public enum Country {
    RUSSIA,
    SPAIN,
    NORTH_KOREA,
    JAPAN;
}