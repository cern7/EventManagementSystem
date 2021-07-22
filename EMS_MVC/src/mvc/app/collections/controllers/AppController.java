package mvc.app.collections.controllers;

import mvc.app.collections.views.*;

import java.util.Scanner;

import mvc.app.collections.models.*;

//17 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class AppController {
	private Scanner input = new Scanner(System.in);
	private LoginView logView;
	private LoginLogic loginUser;
	private RegisterLogic regLogic;
	private RegisterView regView = new RegisterView();
	private CreateEvent newEventView;
	private NewEventLogic newEventLogic;
	private HomePage homePage;
	private ListEvents listEvents;
	private ListEventsView viewEvents;
	private UpdateEvent updateEventDetails;
	private EventUpdateLogic performeEventUpdate;
	private EventModel event;
	private CancelEventNotice cancelNotice;
	private CancelEvent delete;

	public AppController(int i) {
		while (i != 11) {
			switch (i) {
			case 1:
				regView.getUserInfo();
				regLogic = new RegisterLogic(regView.getUsername(), regView.getPassword(), regView.getPasswordConfirm(),
						regView.getFirstName(), regView.getLastName(), regView.getEmail());
			case 2:
				logView = new LoginView();
				loginUser = new LoginLogic();
				if (loginUser.isLoggedIn(logView.getUsername(), logView.getPassword())) {
					homePage = new HomePage();
					i = homePage.homePageView();
				} else {
					System.out.println("Wrong credentials\nTry again");
					i = 2;
				}

				break;
			case 3:
				while (loginUser.getUserId() == 0) {
					logView = new LoginView();
					loginUser = new LoginLogic(logView.getUsername(), logView.getPassword());
				}
				newEventView = new CreateEvent();
				newEventView.setEventdetails();
				newEventView.setOrganiser(loginUser.getUserId());
				newEventLogic = new NewEventLogic(newEventView.getEventName(), newEventView.getEventType(),
						newEventView.getAddressURL(), newEventView.getDescription(), newEventView.getStartDateString(),
						newEventView.getEndDateString(), newEventView.getPlaceLimit(), newEventView.getOrganiser());
				System.out.println("=======================================");
				System.out.println("Event was successfully created");
				homePage = new HomePage();
				i = homePage.homePageView();
				break;
			case 4:
				/* 4 --> List own events */
				listEvents = new ListEvents();
				viewEvents = new ListEventsView(listEvents.userEvents(loginUser.getUserId()));
				homePage = new HomePage();
				i = homePage.homePageView();
				break;
			case 5:
				/* 5 --> Change event details */
				// list the events created by the user
				listEvents = new ListEvents();
				viewEvents = new ListEventsView(listEvents.userEvents(loginUser.getUserId()));

				updateEventDetails = new UpdateEvent();
				int action = updateEventDetails.updateEventUI();
				if (action > 0 && action < 6) {
					event = new EventModel(updateEventDetails.getEventName(), updateEventDetails.getEventType(),
							updateEventDetails.getAddressURL(), updateEventDetails.getDescription(),
							updateEventDetails.getStartDateString(), updateEventDetails.getEndDateString(),
							updateEventDetails.getPlaceLimit(), loginUser.getUserId(), updateEventDetails.getEventID());
					performeEventUpdate = new EventUpdateLogic(action, event);
				}

				homePage = new HomePage();
				i = homePage.homePageView();
				break;
			case 6:
				/* 6 --> Cancel event */
				// list the events created by the user
				listEvents = new ListEvents();
				viewEvents = new ListEventsView(listEvents.userEvents(loginUser.getUserId()));
				System.out.println("Please select the event ID to cancel: ");
				int eventID = input.nextInt();
				
				cancelNotice = new CancelEventNotice();
				if(cancelNotice.cancelWarning()) {
					delete = new CancelEvent(loginUser.getUserId(), eventID);
				}
				homePage = new HomePage();
				i = homePage.homePageView();
				break;
			case 7:
				listEvents = new ListEvents();
				viewEvents = new ListEventsView(listEvents.allEvents());
				homePage = new HomePage();
				i = homePage.homePageView();
				break;
			default:

			}
		}

	}

}
