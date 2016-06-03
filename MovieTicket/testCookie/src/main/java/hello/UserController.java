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
	        model.addAttribute("user", new User());
	        return "login";
	 }
	 
	 @RequestMapping(value="/login", method=RequestMethod.POST)
	    public String greetingSubmit(@ModelAttribute User user, Model model,
	    		HttpServletResponse response) {
		 
//		 repository.save(user);
	    	if(userRepository.findByUsername(user.getUsername()) != null
	    			&& userRepository.findByUsername(user.getUsername()).getPassword().equals(user.getPassword())) {
	    		response.addCookie(new Cookie("token", "user-token"));
	    		return "home";
	    	}
	    	return "login";
	}
	 
	 @RequestMapping(value="/home", method=RequestMethod.GET)
	 public String visitHome(Model model,
	    		@CookieValue(value = "token", defaultValue = "empty") String cookie) {
	    	if(cookie.equals("user-token")) {
	    		
	    		for (User user: userRepository.findAll()) {
	    			String output = "User: " + user.getUsername() + "  Password: " + user.getPassword() +" \n";
	    			System.out.println(output);
	    		}
	    		
	    		return "home";
	    	} else {
	    		return "login";
	    	}
	       
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

	@Override
	public void run(String... args) throws Exception {
	}
}
