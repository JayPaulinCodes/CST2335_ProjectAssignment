package com.cst2335.projectassignment.objects;

import java.util.ArrayList;

// TODO: Add JavaDoc Comment
public class Event {

    private ArrayList<String> flags = new ArrayList<>();
    private Distance distance;
    private EventPriceRange priceRange;
    private EventStartDate startDate;
    private EventStatus status;
    private String additionalInfo;
    private String description;
    private String id;
    private String info;
    private String locale;
    private String name;
    private String pleaseNote;
    private String type;
    private String url;

    // TODO: Add JavaDoc Comment
    public Event(Distance distance, EventPriceRange priceRange, EventStartDate startDate, EventStatus status, String additionalInfo, String description, String id, String info, String locale, String name, String pleaseNote, String type, String url, String... flags) {
        this.distance = distance;
        this.priceRange = priceRange;
        this.startDate = startDate;
        this.status = status;
        this.additionalInfo = additionalInfo;
        this.description = description;
        this.id = id;
        this.info = info;
        this.locale = locale;
        this.name = name;
        this.pleaseNote = pleaseNote;
        this.type = type;
        this.url = url;
        for (int i = 0; i < flags.length; i++) { this.flags.add(flags[i]); }
    }

    /**
     * Accessor method for variable flags
     *
     * @returns value of variable flags
     */
    public ArrayList<String> getFlags() { return flags; }

    /**
     * Mutator method for variable flags
     *
     * @param flags value to assign to variable flags
     */
    public void setFlags(ArrayList<String> flags) { this.flags = flags; }

    /**
     * Accessor method for variable distance
     *
     * @returns value of variable distance
     */
    public Distance getDistance() { return distance; }

    /**
     * Mutator method for variable distance
     *
     * @param distance value to assign to variable distance
     */
    public void setDistance(Distance distance) { this.distance = distance; }

    /**
     * Accessor method for variable priceRange
     *
     * @returns value of variable priceRange
     */
    public EventPriceRange getPriceRange() { return priceRange; }

    /**
     * Mutator method for variable priceRange
     *
     * @param priceRange value to assign to variable priceRange
     */
    public void setPriceRange(EventPriceRange priceRange) { this.priceRange = priceRange; }

    /**
     * Accessor method for variable startDate
     *
     * @returns value of variable startDate
     */
    public EventStartDate getStartDate() { return startDate; }

    /**
     * Mutator method for variable startDate
     *
     * @param startDate value to assign to variable startDate
     */
    public void setStartDate(EventStartDate startDate) { this.startDate = startDate; }

    /**
     * Accessor method for variable status
     *
     * @returns value of variable status
     */
    public EventStatus getStatus() { return status; }

    /**
     * Mutator method for variable status
     *
     * @param status value to assign to variable status
     */
    public void setStatus(EventStatus status) { this.status = status; }

    /**
     * Accessor method for variable additionalInfo
     *
     * @returns value of variable additionalInfo
     */
    public String getAdditionalInfo() { return additionalInfo; }

    /**
     * Mutator method for variable additionalInfo
     *
     * @param additionalInfo value to assign to variable additionalInfo
     */
    public void setAdditionalInfo(String additionalInfo) { this.additionalInfo = additionalInfo; }

    /**
     * Accessor method for variable description
     *
     * @returns value of variable description
     */
    public String getDescription() { return description; }

    /**
     * Mutator method for variable description
     *
     * @param description value to assign to variable description
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Accessor method for variable id
     *
     * @returns value of variable id
     */
    public String getId() { return id; }

    /**
     * Mutator method for variable id
     *
     * @param id value to assign to variable id
     */
    public void setId(String id) { this.id = id; }

    /**
     * Accessor method for variable info
     *
     * @returns value of variable info
     */
    public String getInfo() { return info; }

    /**
     * Mutator method for variable info
     *
     * @param info value to assign to variable info
     */
    public void setInfo(String info) { this.info = info; }

    /**
     * Accessor method for variable locale
     *
     * @returns value of variable locale
     */
    public String getLocale() { return locale; }

    /**
     * Mutator method for variable locale
     *
     * @param locale value to assign to variable locale
     */
    public void setLocale(String locale) { this.locale = locale; }

    /**
     * Accessor method for variable name
     *
     * @returns value of variable name
     */
    public String getName() { return name; }

    /**
     * Mutator method for variable name
     *
     * @param name value to assign to variable name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Accessor method for variable pleaseNote
     *
     * @returns value of variable pleaseNote
     */
    public String getPleaseNote() { return pleaseNote; }

    /**
     * Mutator method for variable pleaseNote
     *
     * @param pleaseNote value to assign to variable pleaseNote
     */
    public void setPleaseNote(String pleaseNote) { this.pleaseNote = pleaseNote; }

    /**
     * Accessor method for variable type
     *
     * @returns value of variable type
     */
    public String getType() { return type; }

    /**
     * Mutator method for variable type
     *
     * @param type value to assign to variable type
     */
    public void setType(String type) { this.type = type; }

    /**
     * Accessor method for variable url
     *
     * @returns value of variable url
     */
    public String getUrl() { return url; }

    /**
     * Mutator method for variable url
     *
     * @param url value to assign to variable url
     */
    public void setUrl(String url) { this.url = url; }
}
