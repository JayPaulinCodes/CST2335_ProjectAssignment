package com.cst2335.projectassignment.objects;

/**
 * Object used to represent and store
 * price ranges for events
 *
 * @author Jacob Paulin
 */
public class EventPriceRange {

    private Double maximum;
    private Double minimum;
    private String currency;

    /**
     * Constructor method for the EventPriceRange class
     * @param currency The currency the price is in
     * @param minimum Bottom of the price range
     * @param maximum Top of the price range
     */
    public EventPriceRange(String currency, Double minimum, Double maximum) {
        this.currency = currency;
        this.minimum = minimum;
        this.maximum = maximum;
    }

    /**
     * Accessor method for variable maximum
     *
     * @return value of variable maximum
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
     * @return value of variable minimum
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
     * @return value of variable currency
     */
    public String getCurrency() { return currency; }

    /**
     * Mutator method for variable currency
     *
     * @param currency value to assign to variable currency
     */
    public void setCurrency(String currency) { this.currency = currency; }

    /**
     * Returns the bottom of the price range in a formatted way
     * @return Bottom of the price range and the currency
     */
    public String getMinimumFormatted() { return String.format("%.2f %s", minimum, currency); }

    /**
     * Returns the top of the price range in a formatted way
     * @return Top of the price range and the currency
     */
    public String getMaximumFormatted() { return String.format("%.2f %s", maximum, currency); }

    /**
     * Formats the price range into a legible format
     * @return The price range
     */
    @Override
    public String toString() {
        return String.format(
                "%s - %s",
                getMinimumFormatted(),
                getMaximumFormatted()
                );
    }
}
