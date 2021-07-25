package mvc.app.collections.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//19 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class EventModel {

	// Class variables
	private String eventName, eventType, addressURL, description, startDateString, endDateString;
	private int placeLimit, organiser, eventID;
//	private Date startDate, endDate;

	// Class variables

	public EventModel() {

	}

	/**
	 * @param eventName
	 * @param eventType
	 * @param addressURL
	 * @param description
	 * @param startDateString
	 * @param endDateString
	 * @param placeLimit
	 * @param organiser
	 * @param eventID
	 */
	public EventModel(String eventName, String eventType, String addressURL, String description, String startDateString,
			String endDateString, int placeLimit, int organiser, int eventID) {
		this.eventName = eventName;
		this.eventType = eventType;
		this.addressURL = addressURL;
		this.description = description;
		this.startDateString = startDateString;
		this.endDateString = endDateString;
		this.placeLimit = placeLimit;
		this.organiser = organiser;
		this.eventID = eventID;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public String getEventName() {
		return eventName;
	}

	public int getOrganiser() {
		return organiser;
	}

	public void setOrganiser(int organiser) {
		this.organiser = organiser;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getAddressURL() {
		return addressURL;
	}

	public void setAddressURL(String addressURL) {
		this.addressURL = addressURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}

	public int getPlaceLimit() {
		return placeLimit;
	}

	public void setPlaceLimit(int placeLimit) {
		this.placeLimit = placeLimit;
	}

	@Override
	public String toString() {
		return "EventModel [ID=" + eventID + ", eventName=" + eventName + ", eventType=" + eventType + ", addressURL="
				+ addressURL + ", description=" + description + ", startDateString=" + startDateString
				+ ", endDateString=" + endDateString + ", placeLimit=" + placeLimit + "]";
	}

}
