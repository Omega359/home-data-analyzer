package com.purplemeow.homerating;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;


@ParametersAreNonnullByDefault
class HomeData {

    private static final Logger LOG = LoggerFactory.getLogger(HomeData.class);

    private String name;
    private String country;
    private String province;
    private String city;
    private Double rValue;

    private int cityScore;
    private int provinceScore;
    private int countryScore;

    /**
     * @throws NumberFormatException is rValue could not be parsed to a double
     */
    HomeData(String name, String address, String rValue) {
        Preconditions.checkArgument(!"".equals(name.trim()));
        Preconditions.checkArgument(!"".equals(address.trim()));
        Preconditions.checkArgument(!"".equals(rValue.trim()));

        this.name = name;
        parseAddress(address);
        this.rValue = Double.valueOf(rValue.trim());

        if (this.rValue < 0 || this.rValue > 50) {
            LOG.warn("log-400: Received out of bounds rValue, expected < 50, received '{}'. Normalizing to bounds", rValue);
            this.rValue = Math.min(50, this.rValue);
            this.rValue = Math.max(0, this.rValue);
        }
    }

    HomeData(String name, String address) {
        this.name = name;
        parseAddress(address);
        this.rValue = null;
    }

    String getName() {
        return name;
    }

    String getCountry() {
        return country;
    }

    String getProvince() {
        return province;
    }

    String getCity() {
        return city;
    }

    Double getRValue() {
        return rValue;
    }

    int getCityScore() {
        return cityScore;
    }

    void setCityScore(int cityScore) {
        this.cityScore = cityScore;
    }

    int getProvinceScore() {
        return provinceScore;
    }

    void setProvinceScore(int provinceScore) {
        this.provinceScore = provinceScore;
    }

    int getCountryScore() {
        return countryScore;
    }

    void setCountryScore(int countryScore) {
        this.countryScore = countryScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, province, city, rValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HomeData homeData = (HomeData) o;
        return Objects.equals(name, homeData.name)
                && Objects.equals(country, homeData.country)
                && Objects.equals(province, homeData.province)
                && Objects.equals(city, homeData.city)
                && Objects.equals(rValue, homeData.rValue);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("country", country)
                .add("province", province)
                .add("city", city)
                .add("rValue", rValue)
                .add("cityScore", cityScore)
                .add("provinceScore", provinceScore)
                .add("countryScore", countryScore)
                .toString();
    }

    private void parseAddress(String address) {
        String[] split = address.split("/");
        for (int i = 0; i < split.length; i++) {
            if (i == 0) {
                country = split[i];
            }
            else if (i == 1) {
                province = split[i];
            }
            else if (i == 2) {
                city = split[i];
            }
        }
    }
}
