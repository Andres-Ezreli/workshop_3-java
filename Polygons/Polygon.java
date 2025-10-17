package Polygons;

public abstract class Polygon {
    public abstract double perimeter();
    public abstract double area();

    public String describe() {
        return String.format("%s Perimeter=%.3f Area=%.3f", this.getClass().getSimpleName(), perimeter(), area());
    }
}
