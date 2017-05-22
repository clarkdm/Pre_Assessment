


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Film_screening {
	
	
	
	private Date start_time;
	private int screen_number;
	private int seats_remaining;
	private Map<Integer, String> bookings;
	private int id;
	
	
	
	public Film_screening(Date start_time, int screen_number, int seats_remaining, int id) {
		setStart_time(start_time);
		setScreen_number(screen_number);
		setSeats_remaining(seats_remaining);
		setId(id);
		
		bookings = new HashMap<Integer, String>();
	
	}



	public Date getStart_time() {
		return start_time;
	}



	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}



	public int getScreen_number() {
		return screen_number;
	}



	public void setScreen_number(int screen_number) {
		this.screen_number = screen_number;
	}



	public int getSeats_remaining() {
		return seats_remaining;
	}



	public void setSeats_remaining(int seats_remaining) {
		this.seats_remaining = seats_remaining;
	}



	public String getBookings() {
		return bookings.toString();
	}



	public boolean add_booking(Integer receipt_number, String ticket_type) {
		if (seats_remaining > 0) {
			bookings.put(receipt_number, ticket_type);
			seats_remaining = seats_remaining - 1;
			return true;
		}
		
		return false;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}








	
	
	
	
	
	
	
}
