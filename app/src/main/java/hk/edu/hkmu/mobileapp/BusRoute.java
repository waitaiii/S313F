package hk.edu.hkmu.mobileapp;

public class BusRoute {
    private String routeNumber;
    private String destination;

    public BusRoute(String routeNumber, String destination) {
        this.routeNumber = routeNumber;
        this.destination = destination;
    }

    public String getRouteNumber() { return routeNumber; }
    public String getDestination() { return destination; }
}
