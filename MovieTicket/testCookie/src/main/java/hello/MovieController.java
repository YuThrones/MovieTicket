package hello;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	 @RequestMapping("/select-cinema/json")
	    public List<Screen> selectPage(@RequestParam(value="movieName", defaultValue="") String movieName,
	    		@RequestParam(value="time", defaultValue="") String time,
	    		@RequestParam(value="cinemaName", defaultValue="") String cinemaName,
	    		Model model) {
		    
		 System.out.println(movieName);
		 System.out.println(time);
		 System.out.println(cinemaName);
		 
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
}
