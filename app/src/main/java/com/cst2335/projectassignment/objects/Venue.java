package com.cst2335.projectassignment.objects;

/**
 * Object to represent and store data
 * regarding a venue for an event
 *
 * @deprecated Not used
 */
public class Venue {

    private Distance distance;
    private String accessibleSeatingDetail;
    private String additionalInfo;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String childRule;
    private String city;
    private String country;
    private String description;
    private String generalRule;
    private String id;
    private String name;
    private String parkingInfo;
    private String postalCode;
    private String state;
    private String url;
    private VenueBoxOffice boxOffice;

    /**
     * Constructor for the venue object
     * @param distance
     * @param accessibleSeatingDetail
     * @param additionalInfo
     * @param addressLine1
     * @param addressLine2
     * @param addressLine3
     * @param childRule
     * @param city
     * @param country
     * @param description
     * @param generalRule
     * @param id
     * @param name
     * @param parkingInfo
     * @param postalCode
     * @param state
     * @param url
     * @param boxOffice
     */
    public Venue(Distance distance, String accessibleSeatingDetail, String additionalInfo, String addressLine1, String addressLine2, String addressLine3, String childRule, String city, String country, String description, String generalRule, String id, String name, String parkingInfo, String postalCode, String state, String url, VenueBoxOffice boxOffice) {
        this.distance = distance;
        this.accessibleSeatingDetail = accessibleSeatingDetail;
        this.additionalInfo = additionalInfo;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.childRule = childRule;
        this.city = city;
        this.country = country;
        this.description = description;
        this.generalRule = generalRule;
        this.id = id;
        this.name = name;
        this.parkingInfo = parkingInfo;
        this.postalCode = postalCode;
        this.state = state;
        this.url = url;
        this.boxOffice = boxOffice;
    }

    /**
     * Accessor method for variable distance
     *
     * @return value of variable distance
     */
    public Distance getDistance() { return distance; }

    /**
     * Mutator method for variable distance
     *
     * @param distance value to assign to variable distance
     */
    public void setDistance(Distance distance) { this.distance = distance; }

    /**
     * Accessor method for variable accessibleSeatingDetail
     *
     * @return value of variable accessibleSeatingDetail
     */
    public String getAccessibleSeatingDetail() { return accessibleSeatingDetail; }

    /**
     * Mutator method for variable accessibleSeatingDetail
     *
     * @param accessibleSeatingDetail value to assign to variable accessibleSeatingDetail
     */
    public void setAccessibleSeatingDetail(String accessibleSeatingDetail) { this.accessibleSeatingDetail = accessibleSeatingDetail; }

    /**
     * Accessor method for variable additionalInfo
     *
     * @return value of variable additionalInfo
     */
    public String getAdditionalInfo() { return additionalInfo; }

    /**
     * Mutator method for variable additionalInfo
     *
     * @param additionalInfo value to assign to variable additionalInfo
     */
    public void setAdditionalInfo(String additionalInfo) { this.additionalInfo = additionalInfo; }

    /**
     * Accessor method for variable addressLine1
     *
     * @return value of variable addressLine1
     */
    public String getAddressLine1() { return addressLine1; }

    /**
     * Mutator method for variable addressLine1
     *
     * @param addressLine1 value to assign to variable addressLine1
     */
    public void setAddressLine1(String addressLine1) { this.addressLine1 = addressLine1; }

    /**
     * Accessor method for variable addressLine2
     *
     * @return value of variable addressLine2
     */
    public String getAddressLine2() { return addressLine2; }

    /**
     * Mutator method for variable addressLine2
     *
     * @param addressLine2 value to assign to variable addressLine2
     */
    public void setAddressLine2(String addressLine2) { this.addressLine2 = addressLine2; }

    /**
     * Accessor method for variable addressLine3
     *
     * @return value of variable addressLine3
     */
    public String getAddressLine3() { return addressLine3; }

    /**
     * Mutator method for variable addressLine3
     *
     * @param addressLine3 value to assign to variable addressLine3
     */
    public void setAddressLine3(String addressLine3) { this.addressLine3 = addressLine3; }

    /**
     * Accessor method for variable childRule
     *
     * @return value of variable childRule
     */
    public String getChildRule() { return childRule; }

    /**
     * Mutator method for variable childRule
     *
     * @param childRule value to assign to variable childRule
     */
    public void setChildRule(String childRule) { this.childRule = childRule; }

    /**
     * Accessor method for variable city
     *
     * @return value of variable city
     */
    public String getCity() { return city; }

    /**
     * Mutator method for variable city
     *
     * @param city value to assign to variable city
     */
    public void setCity(String city) { this.city = city; }

    /**
     * Accessor method for variable country
     *
     * @return value of variable country
     */
    public String getCountry() { return country; }

    /**
     * Mutator method for variable country
     *
     * @param country value to assign to variable country
     */
    public void setCountry(String country) { this.country = country; }

    /**
     * Accessor method for variable description
     *
     * @return value of variable description
     */
    public String getDescription() { return description; }

    /**
     * Mutator method for variable description
     *
     * @param description value to assign to variable description
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Accessor method for variable generalRule
     *
     * @return value of variable generalRule
     */
    public String getGeneralRule() { return generalRule; }

    /**
     * Mutator method for variable generalRule
     *
     * @param generalRule value to assign to variable generalRule
     */
    public void setGeneralRule(String generalRule) { this.generalRule = generalRule; }

    /**
     * Accessor method for variable id
     *
     * @return value of variable id
     */
    public String getId() { return id; }

    /**
     * Mutator method for variable id
     *
     * @param id value to assign to variable id
     */
    public void setId(String id) { this.id = id; }

    /**
     * Accessor method for variable name
     *
     * @return value of variable name
     */
    public String getName() { return name; }

    /**
     * Mutator method for variable name
     *
     * @param name value to assign to variable name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Accessor method for variable parkingInfo
     *
     * @return value of variable parkingInfo
     */
    public String getParkingInfo() { return parkingInfo; }

    /**
     * Mutator method for variable parkingInfo
     *
     * @param parkingInfo value to assign to variable parkingInfo
     */
    public void setParkingInfo(String parkingInfo) { this.parkingInfo = parkingInfo; }

    /**
     * Accessor method for variable postalCode
     *
     * @return value of variable postalCode
     */
    public String getPostalCode() { return postalCode; }

    /**
     * Mutator method for variable postalCode
     *
     * @param postalCode value to assign to variable postalCode
     */
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    /**
     * Accessor method for variable state
     *
     * @return value of variable state
     */
    public String getState() { return state; }

    /**
     * Mutator method for variable state
     *
     * @param state value to assign to variable state
     */
    public void setState(String state) { this.state = state; }

    /**
     * Accessor method for variable url
     *
     * @return value of variable url
     */
    public String getUrl() { return url; }

    /**
     * Mutator method for variable url
     *
     * @param url value to assign to variable url
     */
    public void setUrl(String url) { this.url = url; }

    /**
     * Accessor method for variable boxOffice
     *
     * @return value of variable boxOffice
     */
    public VenueBoxOffice getBoxOffice() { return boxOffice; }

    /**
     * Mutator method for variable boxOffice
     *
     * @param boxOffice value to assign to variable boxOffice
     */
    public void setBoxOffice(VenueBoxOffice boxOffice) { this.boxOffice = boxOffice; }

}
