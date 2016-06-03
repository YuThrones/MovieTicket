package hello;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private CinemaRepository cinemaRepository;
	
	 @RequestMapping(value="/login", method=RequestMethod.GET)
	    public String userForm(Model model) {
		 System.out.println("/login  Get");
	        model.addAttribute("user", new User());
	        return "login";
	 }
	 
	 @RequestMapping(value="/login", method=RequestMethod.POST)
	    public String greetingSubmit(@ModelAttribute User user, Model model,
	    		HttpServletResponse response) {
		 System.out.println("/login  POST");
//		 repository.save(user);
	    	if(userRepository.findByUsername(user.getUsername()) != null
	    			&& userRepository.findByUsername(user.getUsername()).getPassword().equals(user.getPassword())) {
	    		response.addCookie(new Cookie("token", "user-token"));
	    		return "index";
	    	}
	    	return "login";
	}
	
	 
	 @RequestMapping(value="/register", method=RequestMethod.GET)
	    public String register(Model model) {
	        model.addAttribute("user", new User());
	        return "register";
	 }
	 
	 @RequestMapping(value="/register", method=RequestMethod.POST)
	 public String register(@ModelAttribute User user, Model model) {
		 if (userRepository.findByUsername(user.getUsername()) == null) {
			 userRepository.save(user);
			 System.out.println("User  " + user.getUsername() + "  create");
			 return "login";
		 }
		 return "register";
	 }
	 
	 @RequestMapping(value="/index", method=RequestMethod.GET)
	 public String index(Model model) {
		 List<Movie> movieList;
		 movieList = movieRepository.findAll();
		 model.addAttribute("movieList", movieList);
	     return "index";
	 }
	 
	 @RequestMapping("/movie-page")
	    public String moviePage(@RequestParam(value="name", defaultValue="empty") String name, Model model) {
	    	System.out.println("Movie-page");
	    	if (movieRepository.findByMovieName(name).isEmpty()) {
	    		List<Movie> movieList;
	   		    movieList = movieRepository.findAll();
	   		    model.addAttribute("movieList", movieList);
	    		return "index";
	    	}
		    Movie movie = movieRepository.findByMovieName(name).get(0);
		    System.out.println(movie.getMovieName());
	    	model.addAttribute("movie", movie);
	    	return "movie-page";
	    }
	 
//	 @RequestMapping("/select-cinema")
//	    public String selectPage(@RequestParam(value="movieName", defaultValue="") String movieName,
//	    		@RequestParam(value="time", defaultValue="5月19日") String time,
//	    		@RequestParam(value="cinemaName", defaultValue="da") String cinemaName,
//	    		Model model) {
//		    System.out.println("MovieName: " + movieName);
//		    System.out.println("CinemaName: " + cinemaName);
//	    	if (movieRepository.findByMovieName(movieName).isEmpty() || movieName.equals("")) {
//	    		List<Movie> movieList;
//	   		    movieList = movieRepository.findAll();
//	   		    model.addAttribute("movieList", movieList);
//	    		return "index";
//	    	}
//		    Movie movie = movieRepository.findByMovieName(movieName).get(0);
//	    	model.addAttribute("movie", movie);
//	    	
//	    	Cinema cinema;
//	    	if (!cinemaRepository.findByCinemaName(cinemaName).isEmpty()) {
//	    		cinema = cinemaRepository.findByCinemaName(cinemaName).get(0);
//	    	}
//	    	else {
//	    		cinema = cinemaRepository.findAll().get(0);
//	    	}
//	    	model.addAttribute("cinema", cinema);
//	    	
//	    	model.addAttribute("time", time);
//	    	return "select-cinema";
//	    }
	 
	 @RequestMapping(value="/seat-page", method=RequestMethod.GET)
	 public String seatPage(@RequestParam(value="movieName", defaultValue="") String movieName,
	    		@RequestParam(value="time", defaultValue="") String time,
	    		@RequestParam(value="cinemaName", defaultValue="") String cinemaName,
	    		@RequestParam(value="detailTime", defaultValue="") String detailTime,
	    		Model model) {
		 if (movieName.equals("") || time.equals("") || cinemaName.equals("") || detailTime.equals("")
				 || movieRepository.findByMovieName(movieName).isEmpty()
				 || cinemaRepository.findByCinemaName(cinemaName).isEmpty()) {
			 return "index";
		 }
		 
		 System.out.println("CinemaName:" + cinemaName);
		 System.out.println("Cinema:" + movieRepository.findByMovieName(movieName).get(0).getMovieName());
		 System.out.println( cinemaRepository.findByCinemaName(cinemaName).get(0).getCinemaName());
		 
		 Cinema cinema = cinemaRepository.findByCinemaName(cinemaName).get(0);
		 System.out.println(cinema.getScreenMap().get("5月19日").get(0).getTime());
		 
		 System.out.println("done");
		 model.addAttribute("movie", movieRepository.findByMovieName(movieName).get(0));
		 model.addAttribute("cinema", cinemaRepository.findByCinemaName(cinemaName).get(0));
		 model.addAttribute("time", time);
		 model.addAttribute("map", cinema.getScreenMap());
		 return "seat-page";
	 }
	

	@Override
	public void run(String... args) throws Exception {
	}
}
