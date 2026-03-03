package com.example.Shipping_Estimator.util;

/**
 * Utility class for calculating geographic distance
 * between two latitude-longitude coordinates.
 *
 * Uses the Haversine formula to calculate the
 * great-circle distance between two points on Earth.
 */
public class DistanceUtil {

    /**
     * Mean radius of the Earth in kilometers.
     */
    private static final int EARTH_RADIUS = 6371; // km

    /**
     * Calculates distance between two geographic coordinates.
     *
     * @param lat1 Latitude of first location
     * @param lon1 Longitude of first location
     * @param lat2 Latitude of second location
     * @param lon2 Longitude of second location
     * @return Distance in kilometers
     */
    public static double calculateDistance(
            double lat1, double lon1,
            double lat2, double lon2) {

        // Convert differences to radians
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        /*
         * Haversine formula:
         *
         * a = sin²(Δφ/2) + cos φ1 · cos φ2 · sin²(Δλ/2)
         * c = 2 · atan2(√a, √(1−a))
         * distance = R · c
         *
         * where:
         * φ = latitude
         * λ = longitude
         * R = Earth's radius
         */

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2)
                * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Final distance in kilometers
        return EARTH_RADIUS * c;
    }
}