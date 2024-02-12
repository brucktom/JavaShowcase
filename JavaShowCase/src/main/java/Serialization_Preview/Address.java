package Serialization_Preview;

import java.io.Serializable;

public class Address implements Serializable {
    String street;
    String zipCode;
    String town;
    String country;

    public Address (String street, String zipCode, String town, String country) {
        this.street = street;
        this.zipCode = zipCode;
        this.town = town;
        this.country = country;
    }
}
