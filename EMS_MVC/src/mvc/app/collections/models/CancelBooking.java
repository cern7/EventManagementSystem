package mvc.app.collections.models;

import java.sql.DriverManager;
import java.sql.SQLException;

//24 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class CancelBooking {
	private DataBaseConnection db = new DataBaseConnection();

	public CancelBooking() {
		
	}

	/* delete user booking from the database */
	public boolean updateBookingsDB(int userID, int ticketID) {
		boolean deleted = false;
		try {
			db.setCon(DriverManager.getConnection(DataBaseConnection.getDatabaseurl()));

			db.setPrst(db.getCon().prepareStatement("DELETE FROM Bookings WHERE Guest=? AND TicketNo=?"));

			db.getPrst().setInt(1, userID);
			db.getPrst().setInt(2, ticketID);

			if (db.getPrst().executeUpdate() != 0) {
				deleted = true;
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
		return deleted;
	}
}
