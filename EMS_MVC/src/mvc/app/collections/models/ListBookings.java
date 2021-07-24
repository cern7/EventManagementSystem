package mvc.app.collections.models;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.app.collections.models.DataBaseConnection;
import mvc.app.collections.models.EventModel;

//24 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class ListBookings {
	private DataBaseConnection db = new DataBaseConnection();
	private EventModel event;
	private Ticket ticket;
	private List<Ticket> ticketList = new ArrayList<Ticket>();
	private ListEvents bookingEvents = new ListEvents();

	public ListBookings() {

	}

	public List<Ticket> userTicket(int userID) {

		try {
			db.setCon(DriverManager.getConnection(DataBaseConnection.getDatabaseurl()));

			db.setPrst(db.getCon().prepareStatement("SELECT TicketNo, Event FROM Bookings WHERE Guest=?"));

			db.getPrst().setInt(1, userID);

			db.setRs(db.getPrst().executeQuery());

			while (db.getRs().next()) {
				ticket = new Ticket();
				event = new EventModel();
				// gets the ticket object from the db
				ticket.setTicketNo(db.getRs().getInt(1));
				// to set the ticket property (event)
				// first initialising the event object
				// then retrieving from the database the
				// event details using ListEvents.bookedEvent(EventModel) method
				event.setEventID(db.getRs().getInt(2));

				ticket.setEvent(bookingEvents.bookedEvent(event));

				// add ticket to List ticketList
				ticketList.add(ticket);

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
		return ticketList;
	}

}
