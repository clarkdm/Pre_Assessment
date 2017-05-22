import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.util.Pair;

public class run_Ticket {

	public static void main(String[] args) {
		
		run_Ticket run = new run_Ticket();

	}

	Ticket_system_interface Ticket;
	private int movie_pick;
	private int Screen;

	public run_Ticket() {
		
		Ticket = new Ticket_system();
		
		while (true) {
			new_customer();
			Ticket.new_customer();
		}
		
		
		
	}
	private boolean new_customer() {
		movie_pick = pick_movie();
		Screen = pick_Screenings(movie_pick);
		
		
		
		boolean temp = pick_ticket_type();
		while (temp) {
			temp = pick_ticket_type();
			
		}
		
		System.out.println("The total cost of tickets for this movie is £" + Ticket.getCustomer_total_price());
		return true;
		
		
	}
	
	
	private int pick_Screenings(int movie) {
		System.out.println("please pick a Screenings");

		ArrayList<Pair<Integer, Date>> films = Ticket.get_Screenings(movie);
		int i = 0;
		System.out.println(films.size());
		while (i < films.size()) {
			Date temp = films.get(i).getValue();

			SimpleDateFormat dt1 = new SimpleDateFormat("HH:mm EEE dd/MM");

			System.out.println(i + 1 + ". " + dt1.format(temp));
			i++;
		}

		System.out.println(i + 1 + ". Quit");
		int choice = get_choice();
		if (choice == i + 1) {
			System.exit(0);
		}
		return films.get(choice - 1).getKey();

	}

	private boolean pick_ticket_type() {
		ArrayList<Pair<String, Integer>> type = Ticket.get_ticket_type();
		int i = 0;
		while (i < type.size()) {
			
			
			
			
			
			System.out.println(i + 1 + ". " + type.get(i).getKey() +" " + type.get(i).getValue());
			i++;
		}
		System.out.println(i + 1 + ". Done");
		System.out.println(i + 2 + ". Quit");
		int choice = get_choice();
		
		
		
		if (choice == i + 1) {
			return false;
		}
		if (choice == i + 2) {
			System.exit(0);
		}
		return Ticket.book_ticket(movie_pick, Screen, type.get(choice-1).getKey());
		

		
		 
		
		
	}

	private int pick_movie() {

		System.out.println("QA Cinemas");
		System.out.println("please pick a film");

		ArrayList<Pair<Integer, String>> films = Ticket.get_films();
		int i = 0;
		while (i < films.size()) {

			System.out.println(i + 1 + ". " + films.get(i).getValue());
			i++;
		}

		System.out.println(i + 1 + ". Quit");
		int choice = get_choice();
		if (choice == i + 1) {
			System.exit(0);
		}
		return films.get(choice - 1).getKey();

	}

	private int get_choice() {

		System.out.println();
		Scanner scanner = new Scanner(System.in);

		return scanner.nextInt();

	}

}
