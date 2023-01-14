package org.simulation.app.models.mapelement.elementcharacteristics;

public class Gene implements Comparable<Gene> {

    private final Integer value;

    public Gene(int value) {
        if (value < 0 || value > 7) throw new IllegalArgumentException();
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Gene o) {
        return Integer.compare(this.value, o.value);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); // no-no
    }

    @Override
    public int hashCode() {
        return super.hashCode(); // no-no
    }
}
