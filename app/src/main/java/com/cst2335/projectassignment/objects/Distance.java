package com.cst2335.projectassignment.objects;

// TODO: Add JavaDoc Comment
public class Distance {

    private Double distance;
    private String units;

    // TODO: Add JavaDoc Comment
    public Distance(Double distance, String units) {
        this.distance = distance;
        this.units = units;
    }

    /**
     * Accessor method for variable distance
     *
     * @return value of variable distance
     */
    public Double getDistance() { return distance; }

    /**
     * Mutator method for variable distance
     *
     * @param distance value to assign to variable distance
     */
    public void setDistance(Double distance) { this.distance = distance; }

    /**
     * Accessor method for variable units
     *
     * @return value of variable units
     */
    public String getUnits() { return units; }

    /**
     * Mutator method for variable units
     *
     * @param units value to assign to variable units
     */
    public void setUnits(String units) { this.units = units; }

    /**
     * Overriding the default toString method to add our own
     * implementation.
     *
     * @return The distance formatted as a string
     */
    @Override
    public String toString() {
        return String.format("%d %s", distance, units);
    }
}
