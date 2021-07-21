package mvc.app.collections.views;

import java.util.Scanner;

import mvc.app.collections.controllers.AppController;

//19 Jul 2021
/**
 *
 *@author cen7
 *
 */
public class FirstPage {
	private Scanner input = new Scanner(System.in);
	private AppController appControl;
	
	public FirstPage() {
		System.out.println("Wellcome to Event Management System: ");
		System.out.println("Please login(2) or register(1) to use the system");
		int n = input.nextInt();
		while(n != 1 && n != 2) {
			System.out.println("Please insert 2 for login or 1 for register");
			n = input.nextInt();
		}
		appControl = new AppController(n);
	}
}
