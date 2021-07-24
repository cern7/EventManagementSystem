package mvc.app.collections.models;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteException;

//22 Jul 2021
/**
 *
 *@author cen7
 *
 */
public class Bookings {
	private DataBaseConnection db = new DataBaseConnection();
	
	public Bookings() {
		
	}
	public Bookings(Ticket ticket) {
		createBooking(ticket);
	}
	private void createBooking(Ticket ticket) {
		try {
			db.setCon(DriverManager.getConnection(db.getDatabaseurl()));
			db.setPrst(db.getCon().prepareStatement("INSERT INTO Bookings (Guest, Event) VALUES(?,?);"));
			
			db.getPrst().setInt(1, ticket.getHolder().getUserId());
			db.getPrst().setInt(2, ticket.getEvent().getEventID());
			
			db.getPrst().executeUpdate();
			System.out.println("Booking created");
			
		}
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
