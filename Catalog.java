package Lab5;

import javax.xml.bind.annotation.*;
import java.util.HashMap;

@XmlType(name = "catalog")
@XmlRootElement
public class Catalog {
    @XmlElementWrapper(name = "products", nillable = true)
    public HashMap<Integer, Product> products;

    public Catalog() {
        this.products = new HashMap<Integer, Product>();
    }

    public HashMap<Integer, Product> getProducts() {
        return products;
    }

    public int getSize() {
        return products.size();
    }
}
