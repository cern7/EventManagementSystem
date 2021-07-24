package mvc.app.collections.models;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//20 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class ListEvents {
	private DataBaseConnection db = new DataBaseConnection();
	private EventModel event;
	private List<EventModel> eventsList = new ArrayList<EventModel>();

	public ListEvents() {

	}

	public EventModel bookedEvent(EventModel bookedEvent) {
		try {
			db.setCon(DriverManager.getConnection(DataBaseConnection.getDatabaseurl()));

			db.setPrst(db.getCon().prepareStatement("SELECT * FROM Events WHERE EventID=?"));

			db.getPrst().setInt(1, bookedEvent.getEventID());

			db.setRs(db.getPrst().executeQuery());

			while (db.getRs().next()) {
				
				bookedEvent.setEventName(db.getRs().getString(2));
				bookedEvent.setAddressURL(db.getRs().getString(4));
				bookedEvent.setStartDateString(db.getRs().getString(5));
			}

		} catch (SQLException err) {
			// 'printStackTrace()' prints the line number where the exception is thrown
			err.printStackTrace(System.err);
			// 'getMessage()' returns the details of the thrown exception
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
		return bookedEvent;
	}

	public List<EventModel> allEvents() {
		try {
			db.setCon(DriverManager.getConnection(DataBaseConnection.getDatabaseurl()));
			db.setSt(db.getCon().createStatement());
			db.setRs(db.getSt().executeQuery("SELECT * from Events;"));

			while (db.getRs().next()) {

				event = new EventModel();
				event.setEventID(db.getRs().getInt(1));
				event.setEventName(db.getRs().getString(2));
				event.setEventType(db.getRs().getString(3));
				event.setAddressURL(db.getRs().getString(4));
				event.setStartDateString(db.getRs().getString(5));
				event.setEndDateString(db.getRs().getString(6));
				event.setDescription(db.getRs().getString(7));
				event.setPlaceLimit(db.getRs().getInt(8));
				eventsList.add(event);

			}
		} catch (SQLException err) {
			// 'printStackTrace()' prints the line number where the exception is thrown
			err.printStackTrace(System.err);
			// 'getMessage()' returns the details of the thrown exception
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
		return eventsList;

	}

	public List<EventModel> userEvents(int userID) {

		try {
			db.setCon(DriverManager.getConnection(DataBaseConnection.getDatabaseurl()));

			db.setPrst(db.getCon().prepareStatement(
					"SELECT EventID, EventName, EventType, AddressURL, startDate, endDate, Description, PlaceLimitations from Events where Organiser=?;"));

			db.getPrst().setInt(1, userID);

			db.setRs(db.getPrst().executeQuery());
//			db.getPrst().clearParameters();

			while (db.getRs().next()) {

				event = new EventModel();
				event.setEventID(db.getRs().getInt(1));
				event.setEventName(db.getRs().getString(2));
				event.setEventType(db.getRs().getString(3));
				event.setAddressURL(db.getRs().getString(4));
				event.setStartDateString(db.getRs().getString(5));
				event.setEndDateString(db.getRs().getString(6));
				event.setDescription(db.getRs().getString(7));
				event.setPlaceLimit(db.getRs().getInt(8));
				eventsList.add(event);

			}
		} catch (SQLException err) {
			// 'printStackTrace()' prints the line number where the exception is thrown
			err.printStackTrace(System.err);
			// 'getMessage()' returns the details of the thrown exception
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
		return eventsList;

	}

}
