package com.purplemeow.homerating;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


class HomeDataAnalyzer {

    private static final Logger LOG = LoggerFactory.getLogger(HomeDataAnalyzer.class);

    private Map<String, List<HomeData>> homeDataList = new HashMap<>();

    HomeDataAnalyzer() { }

    /**
     * add data to the analyzer for analysis
     */
    void add(HomeData homeData) {
        String key = homeData.getName().toLowerCase();
        homeDataList.putIfAbsent(key, Lists.newArrayList());
        homeDataList.get(key).add(homeData);
    }

    /**
     * Call after adding all home data to the analyzer
     */
    void analyze() {
        // build up a map of country -> homedata
        Map<String, List<HomeData>> countryToHomeData = new HashMap<>();

        for (List<HomeData> homeDataList : homeDataList.values()) {
            for (HomeData homeData : homeDataList) {
                String key = homeData.getCountry().toLowerCase();
                countryToHomeData.putIfAbsent(key, Lists.newArrayList());
                countryToHomeData.get(key).add(homeData);
            }
        }

        // now calculate scores
        for (String country : countryToHomeData.keySet()) {
            SortedSet<HomeData> sorted = new TreeSet<>(Comparator.comparingDouble(HomeData::getRValue));
            sorted.addAll(countryToHomeData.get(country));

            // now update the home data's with this score
            calculateScore(sorted, Locality.COUNTRY);

            // next, within a country do the same thing for region
            Map<String, List<HomeData>> regionToHomeData = new HashMap<>();
            for (HomeData homeData : sorted) {
                String key = homeData.getProvince().toLowerCase();
                regionToHomeData.putIfAbsent(key, Lists.newArrayList());
                regionToHomeData.get(key).add(homeData);
            }

            // now calculate scores
            for (String region : regionToHomeData.keySet()) {
                sorted = new TreeSet<>(Comparator.comparingDouble(HomeData::getRValue));
                sorted.addAll(regionToHomeData.get(region));

                // now update the home data's with this score
                calculateScore(sorted, Locality.PROVINCE);

                // finally, with a region do the same thing for city
                Map<String, List<HomeData>> cityToHomeData = new HashMap<>();
                for (HomeData homeData : sorted) {
                    String key = homeData.getCity().toLowerCase();
                    cityToHomeData.putIfAbsent(key, Lists.newArrayList());
                    cityToHomeData.get(key).add(homeData);
                }

                // now calculate scores
                for (String city : cityToHomeData.keySet()) {
                    sorted = new TreeSet<>(Comparator.comparingDouble(HomeData::getRValue));
                    sorted.addAll(cityToHomeData.get(city));

                    // now update the home data's with this score
                    calculateScore(sorted, Locality.CITY);
                }
            }
        }
    }

    /**
     * Returns a output formatted string if the name/address combination was found, otherwise null
     */
    String query(String name, String address) {
        // find values for the name
        List<HomeData> values = homeDataList.get(name.toLowerCase().trim());
        HomeData analyzed = null;

        if (values != null) {
            for (HomeData homeData : values) {
                if (homeDataHasAddress(homeData, address)) {
                    analyzed = analyzed == null ? homeData : findBestMatch(analyzed, homeData, address);
                }
            }
        }

        if (analyzed != null) {
            return "\"" + name + "\" \"" + address + "\" " + getScore(analyzed, address);
        }
        else {
            LOG.info("log-301: No matching record found for name '{}' and address '{}'", name, address);
        }

        return null;
    }

    private void calculateScore(SortedSet<HomeData> sorted, Locality locality) {
        double i = 1d;

        for (HomeData homeData : sorted) {
            int score = 1 + (((int) (i / (sorted.size() + 1) * 100)) / 10);
            LOG.debug("log-300: {} setting {} score to {} for rValue {}: i: {}, size {}", homeData.getName(), locality, score, homeData.getRValue(),
                      i, sorted.size());

            switch (locality) {
                case COUNTRY:
                    homeData.setCountryScore(score);
                    break;
                case PROVINCE:
                    homeData.setProvinceScore(score);
                    break;
                case CITY:
                    homeData.setCityScore(score);
            }
            i++;
        }
    }

    private boolean homeDataHasAddress(HomeData homeData, String address) {
        String[] split = address.split("/");

        for (int i = 0; i < split.length; i++) {
            String str = split[i].trim();
            if (i == 0 && !homeData.getCountry().equalsIgnoreCase(str)) {
                return false;
            }
            else if (i == 1 && !homeData.getProvince().equalsIgnoreCase(str)) {
                return false;
            }
            else if (i == 2 && !homeData.getCity().equalsIgnoreCase(str)) {
                return false;
            }
        }

        return true;
    }

    private HomeData findBestMatch(HomeData homeData1, HomeData homeData2, String address) {
        String[] split = address.split("/");

        for (int i = 0; i < split.length; i++) {
            String str = split[i].trim();
            if (i == 0) {
                if (homeData1.getCountry().equalsIgnoreCase(str) && homeData2.getCountry().equalsIgnoreCase(str.trim())) {
                    continue;
                }

                return homeData1.getCountry().equalsIgnoreCase(str) ? homeData1 : homeData2;
            }
            else if (i == 1) {
                if (homeData1.getProvince().equalsIgnoreCase(str) && homeData2.getProvince().equalsIgnoreCase(str.trim())) {
                    continue;
                }

                return homeData1.getProvince().equalsIgnoreCase(str) ? homeData1 : homeData2;

            }
            else if (i == 2) {
                if (homeData1.getCity().equalsIgnoreCase(str) && homeData2.getCity().equalsIgnoreCase(str.trim())) {
                    continue;
                }

                return homeData1.getCity().equalsIgnoreCase(str) ? homeData1 : homeData2;
            }
        }

        // they both match, return 1
        LOG.warn("log-303: Found multiple matches for the user/address, returning first match: '{}', '{}'", address, homeData1);
        return homeData1;
    }

    private int getScore(HomeData homeData, String address) {
        String[] split = address.split("/");

        switch (split.length) {
            case 3:
                return homeData.getCityScore();
            case 2:
                return homeData.getProvinceScore();
            default:
                return homeData.getCountryScore();
        }
    }

    private enum Locality {COUNTRY, PROVINCE, CITY}
}
