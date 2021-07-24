package mvc.app.collections.views;

import java.util.List;

import mvc.app.collections.models.Ticket;

//24 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class ListBookingsView {

	public ListBookingsView(List<Ticket> ticketList) {

		for (int i = 0; i < ticketList.size(); i++) {
			System.out.println(ticketList.get(i));
		}
		if (ticketList.size() == 0) {
			System.out.println("You have no bookings.\nTry to book an event");
		}
	}
}
