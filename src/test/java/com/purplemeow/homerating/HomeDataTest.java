package com.purplemeow.homerating;

import org.junit.Test;

import static org.junit.Assert.*;


public class HomeDataTest {

    @Test
    public void testHomeDataConstruction() throws Exception {
        HomeData homeData = new HomeData("a name", "Canada/Ontario/Toronto", "1.0");
        assertEquals("a name", homeData.getName());
        assertEquals("Canada", homeData.getCountry());
        assertEquals("Ontario", homeData.getProvince());
        assertEquals("Toronto", homeData.getCity());
        assertTrue(new Double(1.0).compareTo(homeData.getRValue()) == 0);


        homeData = new HomeData("a name", "Canada/Ontario", "1.001");
        assertEquals("a name", homeData.getName());
        assertEquals("Canada", homeData.getCountry());
        assertEquals("Ontario", homeData.getProvince());
        assertNull(homeData.getCity());
        assertTrue(new Double(1.001).compareTo(homeData.getRValue()) == 0);


        homeData = new HomeData("a name", "Canada/Ontario");
        assertEquals("a name", homeData.getName());
        assertEquals("Canada", homeData.getCountry());
        assertEquals("Ontario", homeData.getProvince());
        assertNull(homeData.getCity());
        assertNull(homeData.getRValue());

        homeData = new HomeData("a name", "Canada/Ontario/Toronto", "-5.0");
        assertTrue(new Double(0.0).compareTo(homeData.getRValue()) == 0);

        homeData = new HomeData("a name", "Canada/Ontario/Toronto", "4445.0");
        assertTrue(new Double(50.0).compareTo(homeData.getRValue()) == 0);
    }
}
