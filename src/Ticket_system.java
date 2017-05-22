import java.io.FileReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;



import javafx.util.Pair;

import java.io.BufferedReader;


public class Ticket_system implements Ticket_system_interface {

	private ArrayList<Film> films;
	private ArrayList<Pair<String, Integer>> prices;
	
	private int customer_index;
	private int current_customer;
	private int customer_total_price;
	

	public Ticket_system() {
		prices = new ArrayList<Pair<String, Integer>>();
		 
		prices.add(new Pair <String, Integer> ("Standard", 8));
		prices.add(new Pair <String, Integer> ("OAP", 6));
		prices.add(new Pair <String, Integer> ("Student", 6));
		prices.add(new Pair <String, Integer> ("Child", 4));
		

		films = new ArrayList<Film>();

		customer_index = 1;
		current_customer = customer_index;
		import_films();
		
		
	}
	public ArrayList<Pair<String, Integer>> get_ticket_type(){
		
		return prices;
		
		
	}
	public boolean new_customer() {
		customer_index = customer_index + 1;
		current_customer = customer_index;
		customer_total_price = 0;

		return true;
	}

	public int getCustomer_total_price() {
		return customer_total_price;
	}

	public boolean book_ticket(int film_id, int Screening_id, String ticket_type) {

		int index = 0;

		while (films.size() > index) {

			if (films.get(index).getFilm_id() == film_id) {

				boolean Booked = films.get(index).add_booking(Screening_id, current_customer, ticket_type);
				if (Booked) {
					Date film_date = films.get(index).get_date(Screening_id);
					
					
					if (film_date.toString().contains("Wed")) {
						for (int i = 0; i < prices.size(); i++) {
							if (prices.get(i).getKey().equals(ticket_type)) {
								customer_total_price = customer_total_price + prices.get(i).getValue() -2;
							}
						}
						
						
					}else {
						for (int i = 0; i < prices.size(); i++) {
							if (prices.get(i).getKey().equals(ticket_type)) {
								customer_total_price = customer_total_price + prices.get(i).getValue();
							}
						}
					}
					return true;
				}
			}
			index++;

			
			
		}

		return false;
	}

	public ArrayList<Pair<Integer, String>> get_films() {
		 ArrayList<Pair<Integer, String>> temp = new ArrayList<Pair<Integer, String>>();
		
		for (int i = 0; i < films.size(); i++) {
			
			temp.add(new Pair <Integer, String> (films.get(i).getFilm_id(), films.get(i).getName()));
			
		}
		return temp;
	}

	public ArrayList<Pair<Integer, Date>> get_Screenings(int film_id) {
		 
		int index = 0;
		while (films.size() > index) {

			if (films.get(index).getFilm_id() == film_id) {
				
				return films.get(index).getScreening();
			}
			index++;
		}
		return null;
	}

	
	
	private void import_films() {
		String thisLine = null;
		try {
			URL url = getClass().getResource("input-file.txt");
			BufferedReader br = new BufferedReader(
                    new FileReader(url.getPath()));
        
        while ((thisLine = br.readLine()) != null) {
        	StringTokenizer msg = new StringTokenizer(thisLine, "#");
        	//System.out.println(msg.nextToken());
        	String name = msg.nextToken();
        	//System.out.println(msg.hasMoreTokens());
			int run_time = Integer.parseInt(msg.nextToken());
			//System.out.println(msg.hasMoreTokens());
			int film_id = Integer.parseInt(msg.nextToken());
			
			
			addfilm(name, run_time, film_id);
        	while (msg.hasMoreElements()) {
        		
        		
        		
				//int film_id = Integer.parseInt(msg.nextToken());
				SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-d-EEE HH:mm"); 
				String date_s = msg.nextToken();
				Date start_time = dt.parse(date_s ); 
				
				int screen_number = Integer.parseInt(msg.nextToken());
				//
				int seats_remaining = Integer.parseInt(msg.nextToken());
				int id = Integer.parseInt(msg.nextToken());
				
				
				addScreening(film_id, start_time, screen_number, seats_remaining, id);
				
			}
        	
        	
        	
        }   
		} catch(Exception e) {
	         e.printStackTrace();
	      }
		//
		//
		


	}
	
	
	private void addfilm(String name, int run_time, int film_id) {

		films.add(new Film(name, run_time, film_id));

	}

	private boolean addScreening(int film_id, Date start_time, int screen_number, int seats_remaining, int id) {

		int index = 0;
		boolean found = false;

		while (films.size() > index && !found) {

			if (films.get(index).getFilm_id() == film_id) {

				films.get(index).addScreening(start_time, screen_number, seats_remaining, id);

				found = true;
				return true;
			}
			index++;

		}

		return false;

	}

}
