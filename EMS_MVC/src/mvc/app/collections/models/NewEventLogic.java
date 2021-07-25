package mvc.app.collections.models;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

//19 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class NewEventLogic {
	private DataBaseConnection db = new DataBaseConnection();

	private EventModel newEvent;

	public NewEventLogic(String eventName, String eventType, String addressURL, String description, String startDate,
			String endDate, int placeLimit, int organiser) {
		newEvent = new EventModel(eventName, eventType, addressURL, description, startDate, endDate, placeLimit,
				organiser, 0);
		dbCreateEvent();
	}

	private void dbCreateEvent() {
		try {
			db.setCon(DriverManager.getConnection(db.getDatabaseurl()));
			db.setPrst(db.getCon().prepareStatement(
					"INSERT INTO Events (Eventname, EventType, AddressURL, startDate, endDate, Description, PlaceLimitations, Organiser) VALUES(?,?,?,?,?,?,?,?);"));
			db.getPrst().setString(1, newEvent.getEventName());
			db.getPrst().setString(2, newEvent.getEventType());
			db.getPrst().setString(3, newEvent.getAddressURL());
			db.getPrst().setString(4, newEvent.getStartDateString());
			db.getPrst().setString(5, newEvent.getEndDateString());
			db.getPrst().setString(6, newEvent.getDescription());
			db.getPrst().setInt(7, newEvent.getPlaceLimit());
			db.getPrst().setInt(8, newEvent.getOrganiser());

			db.getPrst().executeUpdate();
			db.getPrst().clearParameters();
		} // start of the 'catch' block that catches SQL type of exceptions
		catch (SQLException err) {
			// method 'printStackTrace' prints the line number where the exception is thrown
			err.printStackTrace(System.err);
			// method 'getMessage()' returns the details of the thrown exception
			System.out.println(err.getMessage());
		} finally {
			if (db.getRs() != null) {
				try {
					db.getRs().close();
				} catch (SQLException err) {
				}
			}
			if (db.getPrst() != null) {
				try {
					db.getPrst().close();
				} catch (SQLException err) {
				}
			}
			if (db.getCon() != null) {
				try {
					db.getCon().close();
				} catch (SQLException err) {
				}
			}
		}
	}
}
