package mvc.app.collections.views;

import java.util.Scanner;

//22 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class CancelEventNotice {
	private Scanner input = new Scanner(System.in);
	public CancelEventNotice() {

	}

	public boolean cancelWarning() {
		System.out.println("WARNING ALL THE BOOKINGS OF THIS EVENT WILL BE AUTOMATICALLY DELETED");
		System.out.println("**************************************");
		System.out.println("PROCEED?");
		String proceed = input.nextLine();
		if (proceed.equalsIgnoreCase("yes")) {
			return true;
		}else 
			return false;
	}
}