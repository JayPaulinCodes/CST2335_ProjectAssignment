package com.cst2335.projectassignment.objects;

// TODO: Add JavaDoc Comment
public class VenueBoxOffice {

    private String acceptedPayment;
    private String openHours;
    private String phoneNumber;

    // TODO: Add JavaDoc Comment
    public VenueBoxOffice(String acceptedPayment, String openHours, String phoneNumber) {
        this.acceptedPayment = acceptedPayment;
        this.openHours = openHours;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Accessor method for variable acceptedPayment
     *
     * @returns value of variable acceptedPayment
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
     * @returns value of variable openHours
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
     * @returns value of variable phoneNumber
     */
    public String getPhoneNumber() { return phoneNumber; }

    /**
     * Mutator method for variable phoneNumber
     *
     * @param phoneNumber value to assign to variable phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
