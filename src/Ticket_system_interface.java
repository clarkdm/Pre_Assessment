import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

public interface Ticket_system_interface {

	
	
	
	public boolean new_customer();
	
	public int getCustomer_total_price();
	
	public boolean book_ticket(int film_id, int Screening_id, String ticket_type);
	
	public ArrayList<Pair<String, Integer>> get_ticket_type();
	
	public ArrayList<Pair<Integer, String>> get_films();
	
	public ArrayList<Pair<Integer, Date>> get_Screenings(int film_id);
	
	
	
}
