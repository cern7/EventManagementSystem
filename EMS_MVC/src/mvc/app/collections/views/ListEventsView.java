package mvc.app.collections.views;

import java.util.List;

import mvc.app.collections.controllers.*;
import mvc.app.collections.models.EventModel;

//20 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class ListEventsView {

	public ListEventsView(List<EventModel> eventsList) {

		for (int i = 0; i < eventsList.size(); i++) {
			System.out.println(eventsList.get(i));
		}
		if (eventsList.size() == 0) {
			System.out.println("You have no events.\nTry to create.");
		}
	}
	/* Using binary search to find the event selected by the user */
	public EventModel event(List<EventModel> eventsList, int eventID) {
		int lo = 0;
		int hi = eventsList.size() - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if(eventID < eventsList.get(mid).getEventID()) 
				hi = mid - 1;
			else if (eventID > eventsList.get(mid).getEventID())
				lo = mid + 1;
			else 
				return eventsList.get(mid);
		}
		return null;
	}
}
