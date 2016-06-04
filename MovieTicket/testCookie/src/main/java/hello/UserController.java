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
	    public String userForm(@CookieValue(value = "token", defaultValue = "empty") String cookie,
	    		Model model) {
		 System.out.println("/login  Get");
		    model.addAttribute("token", cookie);
	        model.addAttribute("user", new User());
	        return "login";
	 }
	 
	 @RequestMapping(value="/login", method=RequestMethod.POST)
	    public String greetingSubmit(@CookieValue(value = "token", defaultValue = "empty") String cookie,
	    		@ModelAttribute User user, Model model,
	    		HttpServletResponse response) {
		 System.out.println("/login  POST");
		 model.addAttribute("token", cookie);
//		 repository.save(user);
	    	if(userRepository.findByUsername(user.getUsername()) != null
	    			&& userRepository.findByUsername(user.getUsername()).getPassword().equals(user.getPassword())) {
	    		response.addCookie(new Cookie("token", "user-token"));
	    		
	    		return "redirect:/index";
	    	}
	    	return "login";
	}
	
	 
	 @RequestMapping(value="/register", method=RequestMethod.GET)
	    public String register(@CookieValue(value = "token", defaultValue = "empty") String cookie,
	    		Model model) {
		 model.addAttribute("token", cookie);
	        model.addAttribute("user", new User());
	        return "register";
	 }
	 
	 @RequestMapping(value="/register", method=RequestMethod.POST)
	 public String register(@ModelAttribute User user,
			 @CookieValue(value = "token", defaultValue = "empty") String cookie,
			 Model model) {
		 model.addAttribute("token", cookie);
		 if (userRepository.findByUsername(user.getUsername()) == null) {
			 userRepository.save(user);
			 System.out.println("User  " + user.getUsername() + "  create");
			 return "redirect:/login";
		 }
		 return "register";
	 }
	 
	 @RequestMapping(value="/index", method=RequestMethod.GET)
	 public String index(@CookieValue(value = "token", defaultValue = "empty") String cookie,
			 Model model) {
		 model.addAttribute("token", cookie);
		 List<Movie> movieList;
		 movieList = movieRepository.findAll();
		 model.addAttribute("movieList", movieList);
	     return "index";
	 }
	 
	 @RequestMapping("/movie-page")
	    public String moviePage(@RequestParam(value="name", defaultValue="empty") String name,
	    		@CookieValue(value = "token", defaultValue = "empty") String cookie,
	    		Model model) {
	    	System.out.println("Movie-page");
	    	model.addAttribute("token", cookie);
	    	if (movieRepository.findByMovieName(name).isEmpty()) {
	    		return "redirect:/index";
	    	}
		    Movie movie = movieRepository.findByMovieName(name).get(0);
		    System.out.println(movie.getMovieName());
	    	model.addAttribute("movie", movie);
	    	return "movie-page";
	    }
	 
	 
	 @RequestMapping(value="/seat-page", method=RequestMethod.GET)
	 public String seatPage(@RequestParam(value="movieName", defaultValue="") String movieName,
	    		@RequestParam(value="time", defaultValue="") String time,
	    		@RequestParam(value="cinemaName", defaultValue="") String cinemaName,
	    		@RequestParam(value="index") int index,
	    		@CookieValue(value = "token", defaultValue = "empty") String cookie,
	    		Model model) {
		 model.addAttribute("token", cookie);
		 System.out.println(index);
		 if (movieName.equals("") || time.equals("") || cinemaName.equals("")
				 || movieRepository.findByMovieName(movieName).isEmpty()
				 || cinemaRepository.findByCinemaName(cinemaName).isEmpty()) {
			 return "redirect:/index";
		 }
		 
		 List<Screen> screenList = new ArrayList<Screen>();
		 if(!cinemaRepository.findByCinemaName(cinemaName).isEmpty()) {
		 
		 Cinema cinema = cinemaRepository.findByCinemaName(cinemaName).get(0);

		 for(Screen screen:cinema.getScreenListByTime(time)) {
		    	if (screen.getMovieName().equals(movieName)) {
		    		screenList.add(screen);
		    	}
		 }
		 }
		 
		 if (index < 0 || index >= screenList.size()) {
			 return "redirect:/index";
		 }
		 
		 if (!cookie.equals("user-token"))
			 return "redirect:/login";
		 
		 Screen screen = screenList.get(index);
		 
		 
		 
		 
		 System.out.println("done");
		 model.addAttribute("movie", movieRepository.findByMovieName(movieName).get(0));
		 model.addAttribute("cinema", cinemaRepository.findByCinemaName(cinemaName).get(0));
		 model.addAttribute("time", time);
		 model.addAttribute("screen", screen);
		 return "seat-page";
	 }
	 
	 @RequestMapping(value="/order-page", method=RequestMethod.GET)
	 public String orderPage(@RequestParam(value="movieName", defaultValue="") String movieName,
	    		@RequestParam(value="time", defaultValue="") String time,
	    		@RequestParam(value="cinemaName", defaultValue="") String cinemaName,
//	    		@RequestParam(value="screen") Screen screen,
	    		@RequestParam(value="seatList", defaultValue="") String seatList,
	    		@CookieValue(value = "token", defaultValue = "empty") String cookie,
	    		Model model) {
		 model.addAttribute("token", cookie);
		 if (!cookie.equals("user-token"))
			 return "redirect:/login";
		 if (movieName.equals("") || time.equals("") || cinemaName.equals("") || seatList.equals("")
				 || movieRepository.findByMovieName(movieName).isEmpty()
				 || cinemaRepository.findByCinemaName(cinemaName).isEmpty()) {
			 return "redirect:/index";
		 }
		 return "seat-page";
	 }
	

	@Override
	public void run(String... args) throws Exception {
	}
}
