package mvc.app.collections.views;

import java.util.Scanner;

import mvc.app.collections.controllers.AppController;

//19 Jul 2021
/**
 *
 * @author cen7
 *
 */
public class HomePage {
	private int choise;
	private Scanner input = new Scanner(System.in);
	private AppController appControl;

	public HomePage() {
	}
	public int homePageView() {
		System.out.println("=======================================");
		System.out.println("Choose the action:");
		System.out.println("3 --> Create event");			//done
		System.out.println("4 --> List own events");		//done
		System.out.println("5 --> Change event details");	//done
		System.out.println("6 --> Cancel event");
		System.out.println("7 --> Available events");		//done
		System.out.println("8 --> Book event");
		System.out.println("9 --> Cancel booking");
		System.out.println("10 --> View own Bookings");
		System.out.println("11 --> Logout");
		System.out.println("=======================================");
		while(choise < 3 || choise > 11) {
			System.out.println("Please choose form the options above");
			choise = input.nextInt();
		}
		return choise;
	}
	public static void main(String[] args) {
		HomePage h = new HomePage();
		int g = h.homePageView();
		System.out.println(h);
		System.out.println(g);
	}
}
