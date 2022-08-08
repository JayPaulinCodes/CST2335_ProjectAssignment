package com.cst2335.projectassignment.objects;

import android.util.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// TODO: Add JavaDoc Comment
public class EventStartDate {

    private Boolean noSpecificTime;
    private Boolean toBeAssigned;
    private Boolean toBeDetermined;
    private String dateTime;
    private String localDate;
    private String localDateTimeFormatted;
    private LocalDateTime localDateTime;

    public EventStartDate(Boolean noSpecificTime, Boolean toBeAssigned, Boolean toBeDetermined, String dateTime, String localDate) {
        this.noSpecificTime = noSpecificTime;
        this.toBeAssigned = toBeAssigned;
        this.toBeDetermined = toBeDetermined;
        this.dateTime = dateTime;
        this.localDate = localDate;
        this.localDateTime = LocalDateTime.parse(dateTime.replaceAll("Z$", ""));

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        this.localDateTimeFormatted = this.localDateTime.format(dateTimeFormatter);
    }

    /**
     * Accessor method for variable noSpecificTime
     *
     * @return value of variable noSpecificTime
     */
    public Boolean getNoSpecificTime() { return noSpecificTime; }

    /**
     * Mutator method for variable noSpecificTime
     *
     * @param noSpecificTime value to assign to variable noSpecificTime
     */
    public void setNoSpecificTime(Boolean noSpecificTime) { this.noSpecificTime = noSpecificTime; }

    /**
     * Accessor method for variable toBeAssigned
     *
     * @return value of variable toBeAssigned
     */
    public Boolean getToBeAssigned() { return toBeAssigned; }

    /**
     * Mutator method for variable toBeAssigned
     *
     * @param toBeAssigned value to assign to variable toBeAssigned
     */
    public void setToBeAssigned(Boolean toBeAssigned) { this.toBeAssigned = toBeAssigned; }

    /**
     * Accessor method for variable toBeDetermined
     *
     * @return value of variable toBeDetermined
     */
    public Boolean getToBeDetermined() { return toBeDetermined; }

    /**
     * Mutator method for variable toBeDetermined
     *
     * @param toBeDetermined value to assign to variable toBeDetermined
     */
    public void setToBeDetermined(Boolean toBeDetermined) { this.toBeDetermined = toBeDetermined; }

    /**
     * Accessor method for variable dateTime
     *
     * @return value of variable dateTime
     */
    public String getDateTime() { return dateTime; }

    /**
     * Mutator method for variable dateTime
     *
     * @param dateTime value to assign to variable dateTime
     */
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }

    /**
     * Accessor method for variable localDate
     *
     * @return value of variable localDate
     */
    public String getLocalDate() { return localDate; }

    /**
     * Mutator method for variable localDate
     *
     * @param localDate value to assign to variable localDate
     */
    public void setLocalDate(String localDate) { this.localDate = localDate; }

    /**
     * Accessor method for variable localDateTimeFormatted
     *
     * @return value of variable localDateTimeFormatted
     */
    public String getLocalDateTimeFormatted() { return localDateTimeFormatted; }

    /**
     * Mutator method for variable localDateTimeFormatted
     *
     * @param localDateTimeFormatted value to assign to variable localDateTimeFormatted
     */
    public void setLocalDateTimeFormatted(String localDateTimeFormatted) { this.localDateTimeFormatted = localDateTimeFormatted; }

    /**
     * Accessor method for variable localDateTime
     *
     * @return value of variable localDateTime
     */
    public LocalDateTime getLocalDateTime() { return localDateTime; }

    /**
     * Mutator method for variable localDateTime
     *
     * @param localDateTime value to assign to variable localDateTime
     */
    public void setLocalDateTime(LocalDateTime localDateTime) { this.localDateTime = localDateTime; }
}
