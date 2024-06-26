// ---------Welcome to Vjworld----------

package com.vj.CountryLocator;

public class CountryLocatorPerformanceTest {
    public static void main(String[] args) throws Exception {
        CountryLocator locator = new CountryLocator();
        double latitude = 37.7749;
        double longitude = -122.4194;
        int requests = 100;
        long totalTime = 0;

        for (int i = 0; i < requests; i++) {
            long startTime = System.nanoTime();
            locator.getCountryCode(latitude, longitude);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;
            totalTime += duration;
        }

        locator.close();

        double averageTime = (double) totalTime / requests;
        System.out.println("Average time " + averageTime + " ms");
    }
}
