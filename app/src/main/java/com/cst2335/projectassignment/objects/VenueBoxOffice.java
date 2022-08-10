package com.cst2335.projectassignment.objects;

/**
 * Object to represent and store data
 * regarding a venue's box office for
 * an event's venue
 *
 * @deprecated Not used
 */
public class VenueBoxOffice {

    private String acceptedPayment;
    private String openHours;
    private String phoneNumber;

    /**
     * Constructor for the VenueBoxOffice object
     * @param acceptedPayment
     * @param openHours
     * @param phoneNumber
     */
    public VenueBoxOffice(String acceptedPayment, String openHours, String phoneNumber) {
        this.acceptedPayment = acceptedPayment;
        this.openHours = openHours;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Accessor method for variable acceptedPayment
     *
     * @return value of variable acceptedPayment
     */
    public String getAcceptedPayment() { return acceptedPayment; }

    /**
     * Mutator method for variable acceptedPayment
     *
     * @param acceptedPayment value to assign to variable acceptedPayment
     */
    public void setAcceptedPayment(String acceptedPayment) { this.acceptedPayment = acceptedPayment; }

    /**
     * Accessor method for variable openHours
     *
     * @return value of variable openHours
     */
    public String getOpenHours() { return openHours; }

    /**
     * Mutator method for variable openHours
     *
     * @param openHours value to assign to variable openHours
     */
    public void setOpenHours(String openHours) { this.openHours = openHours; }

    /**
     * Accessor method for variable phoneNumber
     *
     * @return value of variable phoneNumber
     */
    public String getPhoneNumber() { return phoneNumber; }

    /**
     * Mutator method for variable phoneNumber
     *
     * @param phoneNumber value to assign to variable phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
