package mvc.app.collections.views;

import java.util.Scanner;

//21 Jul 2021
/**
 *
 *@author cen7
 *
 */
public class UpdateEvent extends CreateEvent{
	private Scanner input = new Scanner(System.in);
	private int eventID;
	
	public UpdateEvent() {
		
		
		
	}
	public int updateEventUI() {
		System.out.println("=======================================");
		System.out.println("Event update");
		System.out.println("=======================================");
		System.out.println("Please select the event ID you would like to update:");
		setEventID(input.nextInt());
		System.out.println("Please select the action to perform:");
		System.out.println("1. Change event type\n2. Change the event guest limit");
		System.out.println("3. Change event address\n4. Change event name");
		System.out.println("5. Change event description\n6. Go back");
		int c = input.nextInt();
		switch(c) {
		case 1:
			System.out.println("New event type: online / physical ");
			input.nextLine();
			setEventType(input.nextLine());
			break;
		case 2:
			System.out.println("New guest limit:");
			input.nextLine();
			setPlaceLimit(input.nextInt());
			break;
		case 3:
			System.out.println("New event address:");
			input.nextLine();
			setAddressURL(input.nextLine());
			break;
		case 4:
			System.out.println("New event name:");
			input.nextLine();
			setEventName(input.nextLine());
			break;
		case 5:
			System.out.println("New event description:");
			input.nextLine();
			setDescription(input.nextLine());
			break;
		case 6:
			c = 0;
			break;
		default:
			break;
		}
		return c;
	}
	public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public int getOrganiser() {
		return organiser;
	}
}
