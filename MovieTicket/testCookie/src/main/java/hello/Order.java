package hello;

import org.springframework.data.annotation.Id;

public class Order {
	@Id
	private String id;
	
	public String movieName;
	public String cinemaName;
	public String date;
	public String detailTime;
	public SeatItem seatItem;
	public String userName;
	public String room;
	public double price;
}
