package juanlurodr.solarsystembirthday;

class Planet {

    private static int MILLISECONDS_IN_YEAR = 86400*1000;

    private final String name;
    private final double tropicalOrbitPeriod;

    Planet(String name, double tropicalOrbitPeriod) {
        this.name = name;
        this.tropicalOrbitPeriod = tropicalOrbitPeriod;
    }

    public double getTropicalOrbitPeriod() {
        return tropicalOrbitPeriod;
    }

    public long getTropicalOrbitPeriodInMilliseconds() { return (long) (tropicalOrbitPeriod*MILLISECONDS_IN_YEAR); }

    public String toString() {
        return name;
    }

}
