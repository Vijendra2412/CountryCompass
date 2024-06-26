// ---------Welcome to Vjworld----------

package com.vj.CountryLocator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CountryLocatorTest {
    private CountryLocator locator;

    @Before
    public void setUp() throws Exception {
        locator = new CountryLocator();
    }

    @After
    public void tearDown() throws Exception {
        locator.close();
    }

    @Test
    public void testGetCountryCode() throws Exception {
        String expected = "US";
        String actual = locator.getCountryCode(37.7749, -122.4194);
        assertEquals(expected, actual);
    }


}
