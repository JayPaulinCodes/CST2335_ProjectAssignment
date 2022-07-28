package com.cst2335.projectassignment.objects;

// TODO: Add JavaDoc Comment
public class EventPriceRange {

    private Double maximum;
    private Double minimum;
    private String currency;

    // TODO: Add JavaDoc Comment
    public EventPriceRange(String currency, Double minimum, Double maximum) {
        this.currency = currency;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    /**
     * Accessor method for variable maximum
     *
     * @returns value of variable maximum
     */
    public Double getMaximum() { return maximum; }

    /**
     * Mutator method for variable maximum
     *
     * @param maximum value to assign to variable maximum
     */
    public void setMaximum(Double maximum) { this.maximum = maximum; }

    /**
     * Accessor method for variable minimum
     *
     * @returns value of variable minimum
     */
    public Double getMinimum() { return minimum; }

    /**
     * Mutator method for variable minimum
     *
     * @param minimum value to assign to variable minimum
     */
    public void setMinimum(Double minimum) { this.minimum = minimum; }

    /**
     * Accessor method for variable currency
     *
     * @returns value of variable currency
     */
    public String getCurrency() { return currency; }

    /**
     * Mutator method for variable currency
     *
     * @param currency value to assign to variable currency
     */
    public void setCurrency(String currency) { this.currency = currency; }

    // TODO: Add JavaDoc Comment
    public String getMinimumFormated() { return String.format("%d %s", minimum, currency); }

    // TODO: Add JavaDoc Comment
    public String getMaximumFormated() { return String.format("%d %s", maximum, currency); }
}
