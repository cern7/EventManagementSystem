package mvc.app.collections.models;

import java.sql.DriverManager;
import java.sql.SQLException;

//22 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class CancelEvent {
	private DataBaseConnection db = new DataBaseConnection();

	public CancelEvent() {

	}

	public CancelEvent(int userID, int eventID) {
		updateEventDB(userID, eventID);
	}

	/* delete the event from the database */
	private void updateEventDB(int userID, int eventID) {
		try {
			db.setCon(DriverManager.getConnection(DataBaseConnection.getDatabaseurl()));
			db.setPrst(db.getCon().prepareStatement("DELETE FROM Events WHERE EventID=? AND Organiser=?;"));
			db.getPrst().setInt(1, eventID);
			db.getPrst().setInt(2, userID);

			if (db.getPrst().executeUpdate() != 0) {
				System.out.println("Event deleted");
			} else
				System.out.println("Event wasn't deleted");

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
	}
}
