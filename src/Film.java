
import java.util.ArrayList;
import java.util.Date;


import javafx.util.Pair;

public class Film {

	private String name;
	
	private int run_time;
	private int film_id;
	private ArrayList<Film_screening> screening;

	public Film(String name, int run_time, int film_id) {
		setFilm_id(film_id);
		this.name = name;
		
		this.run_time = run_time;

		screening = new ArrayList<Film_screening>();
	}

	public String getName() {
		return name;
	}



	public int getRun_time() {
		return run_time;
	}

	public ArrayList<Pair<Integer, Date>> getScreening() {
		ArrayList<Pair<Integer, Date>> temp = new ArrayList<Pair<Integer, Date>>();
		
	
		for (int i = 0; i < screening.size(); i++) {
			temp.add(new Pair <Integer, Date> (screening.get(i).getId(), screening.get(i).getStart_time()));
			

		}
		return temp;

	}

	public boolean addScreening(Date start_time, int screen_number, int seats_remaining, int id) {
		
		return screening.add(new Film_screening(start_time, screen_number, seats_remaining, id));
		  
	}

	public int getFilm_id() {
		return film_id;
	}

	public void setFilm_id(int film_id) {
		this.film_id = film_id;
	}
	
	public boolean add_booking(int Screening_id, int current_customer, String ticket_type) {
		int index = 0;
		boolean found = false;

		while (screening.size() > index && !found) {

			if (screening.get(index).getId() == Screening_id) {

				screening.get(index).add_booking(current_customer, ticket_type);

				found = true;
				return true;
			}
			index++;

		}

		return false;
	}
	
	public Date get_date(int Screening_id) {
		int index = 0;
		

		while (screening.size() > index) {

			if (screening.get(index).getId() == Screening_id) {

				return screening.get(index).getStart_time();

			
			}
			index++;

		}

		return null;
	}
}




















