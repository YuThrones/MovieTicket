package hello;

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
	    public PageItem selectPage(@RequestParam(value="movieName", defaultValue="") String movieName,
	    		Model model) {
		    PageItem pageItem = new PageItem();
		    
		    if (movieRepository.findByMovieName(movieName).isEmpty()) {
		    	pageItem.movie = null;
		    }
		    else {
		    	pageItem.movie = movieRepository.findByMovieName(movieName).get(0);
		    }
		    
		    
		    pageItem.cinemaList = cinemaRepository.findAll();
		    
		    pageItem.timeList.add("5月19日");
		    pageItem.timeList.add("5月20日");
		    pageItem.timeList.add("5月21日");
		    pageItem.timeList.add("5月22日");
		    
	    	return pageItem;
	    }
}
