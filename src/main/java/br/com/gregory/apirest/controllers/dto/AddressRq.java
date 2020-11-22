package br.com.gregory.apirest.controllers.dto;

/*
 * Request Class
 * */

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddressRq {

    @NotNull
    @NotEmpty
    private String streetName;

    @NotNull
    @NotEmpty
    private Integer number;

    private String complement;

    @NotNull
    @NotEmpty
    private String neighbourhood;

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    private String state;

    @NotNull
    @NotEmpty
    private String country;

    @NotNull
    @NotEmpty
    private Integer zipcode;

    private Float latitude;
    private Float longitude;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}
