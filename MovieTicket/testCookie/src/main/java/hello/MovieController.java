package hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private CinemaRepository cinemaRepository;
	
	
	//返回一个包含电影院信息的json以在页面上显示
	 @RequestMapping("/select-cinema/json")
	    public List<Screen> selectPage(@RequestParam(value="movieName", defaultValue="") String movieName,
	    		@RequestParam(value="time", defaultValue="") String time,
	    		@RequestParam(value="cinemaName", defaultValue="") String cinemaName,
	    		Model model) {
		    
//		 System.out.println(movieName);
//		 System.out.println(time);
//		 System.out.println(cinemaName);
		 
		    List<Screen> screenList = new ArrayList<Screen>();
		   if(cinemaRepository.findByCinemaName(cinemaName).isEmpty()) return screenList;
		 
		    Cinema cinema = cinemaRepository.findByCinemaName(cinemaName).get(0);

		    for(Screen screen:cinema.getScreenListByTime(time)) {
		    	if (screen.getMovieName().equals(movieName)) {
		    		screenList.add(screen);
		    	}
		    }
		    
	    	return screenList;
	}
	 
	 
	//根据数据库中场次座位信息返回一个json
	 @RequestMapping("/seat-page/json")
	 public SeatPageItem seatPage(@RequestParam(value="movieName", defaultValue="") String movieName,
	    		@RequestParam(value="time", defaultValue="5月19日") String time,
	    		@RequestParam(value="cinemaName", defaultValue="da") String cinemaName,
	    		Model model) {
		    SeatPageItem seatPageItem = new SeatPageItem();
		    
		    if (movieRepository.findByMovieName(movieName).isEmpty()) {
		    	seatPageItem.movie = null;
		    }
		    else {
		    	seatPageItem.movie = movieRepository.findByMovieName(movieName).get(0);
		    }
		    
		    seatPageItem.time = time;
		    if (cinemaRepository.findByCinemaName(cinemaName).isEmpty()) {
		    	seatPageItem.cinema = null;
		    	
		    }
		    else {
		    	seatPageItem.cinema = cinemaRepository.findByCinemaName(cinemaName).get(0);
		    }
		    
	    	return seatPageItem;
	}
	 //接受订票确认请求，然后写数据库
	 @RequestMapping(value="/order-page/commit", method=RequestMethod.GET)
	 public String orderPage(@RequestParam(value="movieName", defaultValue="") String movieName,
	    		@RequestParam(value="time", defaultValue="") String time,
	    		@RequestParam(value="cinemaName", defaultValue="") String cinemaName,
//	    		@RequestParam(value="screen") Screen screen,
	    		@RequestParam(value="detailTime", defaultValue="") String detailTime,
	    		@RequestParam(value="seat", defaultValue="") String seat,
	    		@CookieValue(value = "token", defaultValue = "empty") String cookie,
	    		Model model) {
		 model.addAttribute("token", cookie);
		 if (cookie.equals("empty"))
			 return "failed";
		 if (movieName.equals("") || time.equals("") || cinemaName.equals("") 
				 || detailTime.equals("")
				 || seat.equals("")
//				 || screen == null
				 ||  movieRepository.findByMovieName(movieName).isEmpty()
				 || cinemaRepository.findByCinemaName(cinemaName).isEmpty()) {
			 return "failed";
		 }
		 
		 List<SeatItem> seatList = getSeatItemList(seat);
		 Cinema cinema = cinemaRepository.findByCinemaName(cinemaName).get(0);
		 cinemaRepository.delete(cinema);
//		 showCinema(cinema);
		 
		 List<Screen> screenList = cinema.getScreenListByTime(time);
		 Screen screen = new Screen();
		 for (Screen temp : screenList) {
			 if (temp.getMovieName().equals(movieName) && temp.getTime().equals(detailTime)) {
				 screen = temp;
			 }
		 }
		 
		 for (SeatItem temp : seatList) {
			 screen.seatOrdered[temp.rowIndex][temp.colIndex] = true;
		 }
		 
		 cinema.updateScreenList(time, screenList);
		 cinemaRepository.save(cinema);
//		 showCinema(cinema);
		 
		 return "success";
	 }
		 
		 
		 //把从客户端得到的座位下标信息由一个长String转化为一个SeatItem列表
		 public List<SeatItem> getSeatItemList(String seat) {
			 List<SeatItem> seatList = new ArrayList<SeatItem>();
			 for (String seatTemp : seat.split("-")) {
				 SeatItem temp = new SeatItem();
				 String[] tempList = seatTemp.split(",");
				 temp.rowIndex = Integer.parseInt(tempList[0]);
				 temp.colIndex = Integer.parseInt(tempList[1]);
				 seatList.add(temp);
			 }
			 return seatList;
		 }
		 
		 public void showCinema(Cinema cinema) {
				System.out.println("Cinema Name: " + cinema.getCinemaName());
				Map<String, List<Screen>> screenMap = cinema.getScreenMap();
				Set<String> timeSet = screenMap.keySet();
				for (String time:timeSet) {
					System.out.println("  Time: " + time);
					for (Screen screen:screenMap.get(time)) {
						System.out.println("    MovieName: " + screen.getMovieName());
						System.out.println("    Price: " + screen.getPrice());
						System.out.println("    Roome: " + screen.getRoom());
						System.out.println("    Time: " + screen.getTime());
					}
				}
			}
}
