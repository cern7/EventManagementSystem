package mvc.app.collections.views;
import java.util.List;

import mvc.app.collections.controllers.*;
import mvc.app.collections.models.EventModel;
//20 Jul 2021
/**
 *
 *@author cen7
 *
 */
public class ListEventsView {
	
	public ListEventsView(List<EventModel> eventsList) {
		for(int i = 0; i < eventsList.size(); i++) {
			System.out.println(eventsList.get(i));
		}
	}
}
