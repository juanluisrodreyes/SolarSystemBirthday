package juanlurodr.solarsystembirthday;

class Planet {

    private final String name;
    private final double tropicalOrbitPeriod;

    Planet(String name, double tropicalOrbitPeriod) {
        this.name = name;
        this.tropicalOrbitPeriod = tropicalOrbitPeriod;
    }

    public double getTropicalOrbitPeriod() {
        return tropicalOrbitPeriod;
    }

    public String toString() {
        return name;
    }

}
