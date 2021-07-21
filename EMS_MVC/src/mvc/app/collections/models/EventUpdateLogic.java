package mvc.app.collections.models;

import java.sql.DriverManager;
import java.sql.SQLException;

//21 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class EventUpdateLogic {
	private DataBaseConnection db = new DataBaseConnection();

	public EventUpdateLogic() {

	}

	public EventUpdateLogic(int action, EventModel event) {
		updateEventDB(action, event);
	}

	private void updateEventDB(int action, EventModel event) {
		String column = "";
		String arg1 = "";
		switch (action) {
		case 1:
			column = "EvenType";
			arg1 = event.getEventType();
			break;
		case 2:
			column = "PlaceLimitations";
			arg1 = String.valueOf(event.getPlaceLimit());
			break;
		case 3:
			column = "AddressURL";
			arg1 = event.getAddressURL();
			break;
		case 4:
			column = "EventName";
			arg1 = event.getEventName();
			break;
		case 5:
			column = "Description";
			arg1 = event.getDescription();
			break;
		default:
			break;
		}
		String prepareStm = "UPDATE Events SET " + column + "=? WHERE EventID=? and Organiser=?; ";

		try {
			db.setCon(DriverManager.getConnection(db.getDatabaseurl()));
			db.setPrst(db.getCon().prepareStatement(prepareStm));
			db.getPrst().setString(1, arg1);
			db.getPrst().setInt(2, event.getEventID());
			db.getPrst().setInt(3, event.getOrganiser());

			if (db.getPrst().executeUpdate() == 1) {
				System.out.println("Event details updated");
			} else
				System.out.println("Something wrong try again");

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
