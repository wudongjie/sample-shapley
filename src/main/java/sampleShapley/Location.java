package sampleShapley;

public class Location {
    private double longitude;
    private double latitude;
    public Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "sampleShapley.Location{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}