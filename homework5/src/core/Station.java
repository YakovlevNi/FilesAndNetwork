package core;

public class Station {

    private String name;
    private Line line;

    public Station(String name, Line line) {
        this.line = line;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Line getLine() {
        return line;
    }

    @Override
    public String toString() {
        return name;
    }
}