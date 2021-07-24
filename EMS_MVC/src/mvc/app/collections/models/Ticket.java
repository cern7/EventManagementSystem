package mvc.app.collections.models;

//22 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class Ticket {
	private int ticketNo;
	private LoginLogic holder;
	private EventModel event;

	public Ticket() {

	}

	/**
	 * @param holder
	 * @param event
	 */
	public Ticket(LoginLogic holder, EventModel event) {

		this.holder = holder;
		this.event = event;
	}

	public int getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}

	public LoginLogic getHolder() {
		return holder;
	}

	public void setHolder(LoginLogic holder) {
		this.holder = holder;
	}

	public EventModel getEvent() {
		return event;
	}

	public void setEvent(EventModel event) {
		this.event = event;
	}

	@Override
	public String toString() {
		return "Booking [ticketNo=" + ticketNo + ", event name=" + getEvent().getEventName() + ", address="
				+ getEvent().getAddressURL() + ", start date=" + getEvent().getStartDateString() + "]";
	}

}
