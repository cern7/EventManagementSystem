package mvc.app.collections.views;

import java.util.Scanner;

//19 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class CreateEvent {

	private Scanner input = new Scanner(System.in);
	protected String eventName, eventType, addressURL, description, startDateString, endDateString;
	protected int placeLimit;
	protected int organiser;

	public CreateEvent() {
		
	}

	public void setEventdetails() {
		System.out.println("=======================================");
		System.out.println("Event registration");
		System.out.println("=======================================");
		System.out.print("Event name: ");
		setEventName(input.nextLine());
		System.out.println("Is it a face-to-face or online event");
		System.out.print("physical/online ");
		setEventType(input.next());
		System.out.print("Address or URL: ");
		input.nextLine();
		setAddressURL(input.nextLine());
		System.out.print("Start date: (dd.mm.yyyy) ");
		setStartDateString(input.next());
		System.out.print("End date: (dd.mm.yyyy) ");
		setEndDateString(input.next());
		System.out.print("Description: ");
		input.nextLine();
		setDescription(input.nextLine());
		if (getEventType().equalsIgnoreCase("online")) {
			setPlaceLimit(9999);
		} else {
			System.out.print("Place limitations of the event: ");
			setPlaceLimit(input.nextInt());
		}

	}

	public String getEventName() {
		return eventName;
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

	public int getOrganiser() {
		return organiser;
	}

	public void setOrganiser(int organiser) {
		this.organiser = organiser;
	}

}
