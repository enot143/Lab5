package Lab5;

import javax.xml.bind.annotation.*;

@XmlType(name = "unit")
@XmlEnum(String.class)
public enum UnitOfMeasure {
    KILOGRAMS,
    METERS,
    CENTIMETERS,
    SQUARE_METERS,
    MILLILITERS;
}
