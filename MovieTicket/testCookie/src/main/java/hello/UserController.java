package hello;

import java.security.KeyStore.TrustedCertificateEntry;
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
	//操作中可能会涉及的三个数据库
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private CinemaRepository cinemaRepository;
	
	 //浏览器发送带有用户名密码的POST请求，从数据库中检索，如果找到相应User则成功登陆，添加cookie，跳转到index界面，如果没有符合的条目，则刷新登陆页面，重新输入。
	 @RequestMapping("/")
	    public String home(@CookieValue(value = "token", defaultValue = "empty") String cookie,
				 Model model){
	    	return "redirect:/index";
	}
	
	//请求登陆页面，如果找到已有cookie，说明之前登陆过，直接跳到index界面
	 @RequestMapping(value="/login", method=RequestMethod.GET)
	    public String userForm(@CookieValue(value = "token", defaultValue = "empty") String cookie,
	    		Model model) {
//		 System.out.println("/login  Get");
		 if (!cookie.equals("empty"))
			 return "redirect:/index";
		 
		    model.addAttribute("token", cookie);
	        model.addAttribute("user", new User());
	        return "login";
	 }
	 
	 //浏览器发送带有用户名密码的POST请求，从数据库中检索，如果找到相应User则成功登陆，添加cookie，跳转到index界面，如果没有符合的条目，则刷新登陆页面，重新输入。
	 @RequestMapping(value="/login", method=RequestMethod.POST)
	    public String greetingSubmit(@CookieValue(value = "token", defaultValue = "empty") String cookie,
	    		@ModelAttribute User user, Model model,
	    		HttpServletResponse response) {
//		 System.out.println("/login  POST");
		 model.addAttribute("token", cookie);
//		 repository.save(user);
	    	if(userRepository.findByUsername(user.getUsername()) != null
	    			&& userRepository.findByUsername(user.getUsername()).getPassword().equals(user.getPassword())) {
	    		response.addCookie(new Cookie("token", user.getUsername()));
	    		
	    		return "redirect:/index";
	    	}
	    	return "login";
	}
	
	 //渲染注册页面
	 @RequestMapping(value="/register", method=RequestMethod.GET)
	    public String register(@CookieValue(value = "token", defaultValue = "empty") String cookie,
	    		Model model) {
		 model.addAttribute("token", cookie);
	        model.addAttribute("user", new User());
	        return "register";
	 }
	 
	 //提交注册信息，注册成功后返回登录界面
	 @RequestMapping(value="/register", method=RequestMethod.POST)
	 public String register(@ModelAttribute User user,
			 @CookieValue(value = "token", defaultValue = "empty") String cookie,
			 Model model) {
		 model.addAttribute("token", cookie);
		 if (userRepository.findByUsername(user.getUsername()) == null) {
			 userRepository.save(user);
//			 System.out.println("User  " + user.getUsername() + "  create");
			 return "redirect:/login";
		 }
		 return "register";
	 }
	 
	 //返回首页信息
	 @RequestMapping(value="/index", method=RequestMethod.GET)
	 public String index(@CookieValue(value = "token", defaultValue = "empty") String cookie,
			 Model model) {
		 model.addAttribute("token", cookie);
		 List<Movie> movieList;
		 movieList = movieRepository.findAll();
		 model.addAttribute("movieList", movieList);
	     return "index";
	 }
	 
	 //返回电影简介页面，显示电影详细信息
	 @RequestMapping("/movie-page")
	    public String moviePage(@RequestParam(value="name", defaultValue="empty") String name,
	    		@CookieValue(value = "token", defaultValue = "empty") String cookie,
	    		Model model) {
//	    	System.out.println("Movie-page");
	    	model.addAttribute("token", cookie);
	    	if (movieRepository.findByMovieName(name).isEmpty()) {
	    		return "redirect:/index";
	    	}
		    Movie movie = movieRepository.findByMovieName(name).get(0);
//		    System.out.println(movie.getMovieName());
	    	model.addAttribute("movie", movie);
	    	return "movie-page";
	    }
	 
	 //返回电影座位页面
	 @RequestMapping(value="/seat-page", method=RequestMethod.GET)
	 public String seatPage(@RequestParam(value="movieName", defaultValue="") String movieName,
	    		@RequestParam(value="time", defaultValue="") String time,
	    		@RequestParam(value="cinemaName", defaultValue="") String cinemaName,
	    		@RequestParam(value="index") int index,
	    		@CookieValue(value = "token", defaultValue = "empty") String cookie,
	    		Model model) {
		 model.addAttribute("token", cookie);
//		 System.out.println(index);
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
		 
		 if (cookie.equals("empty"))
			 return "redirect:/login";
		 
		 Screen screen = screenList.get(index);
		 
		 
		 
		 
//		 System.out.println("done");
		 model.addAttribute("movie", movieRepository.findByMovieName(movieName).get(0));
		 model.addAttribute("cinema", cinemaRepository.findByCinemaName(cinemaName).get(0));
		 model.addAttribute("time", time);
		 model.addAttribute("screen", screen);
		 return "seat-page";
	 }
	 
	 //根据选座页面提交的信息，显示订单页面，根据电影名，电影院名，场次时间，座位下标来确定订单内容
	 @RequestMapping(value="/order-page", method=RequestMethod.GET)
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
			 return "redirect:/login";
		 if (movieName.equals("") || time.equals("") || cinemaName.equals("") 
				 || detailTime.equals("")
				 || seat.equals("")
//				 || screen == null
				 ||  movieRepository.findByMovieName(movieName).isEmpty()
				 || cinemaRepository.findByCinemaName(cinemaName).isEmpty()) {
			 return "redirect:/index";
		 }
//		 System.out.println(cinemaName);
//		 System.out.println(movieName);
//		 System.out.println(time);
//		 System.out.println(screen.getTime());
//		 System.out.println(detailTime);
//		 System.out.println(seat);
		 
		 List<SeatItem> seatList = getSeatItemList(seat);
		 Cinema cinema = cinemaRepository.findByCinemaName(cinemaName).get(0);
//		 cinemaRepository.delete(cinema);
		 List<Screen> screenList = cinema.getScreenListByTime(time);
		 Screen screen = new Screen();
		 for (Screen temp : screenList) {
			 if (temp.getMovieName().equals(movieName) && temp.getTime().equals(detailTime)) {
				 screen = temp;
			 }
		 }
		 
		 List<Order> orderList = new ArrayList<Order>();
		 
		 
		 for (SeatItem temp : seatList) {
//			 screen.seatOrdered[temp.rowIndex][temp.colIndex] = true;
			 Order order = new Order();
			 order.userName = cookie;
			 order.cinemaName = cinemaName;
			 order.movieName = movieName;
			 order.date = time;
			 order.detailTime = detailTime;
			 order.seatItem = temp;
			 order.room = screen.getRoom();
			 order.price = screen.getPrice();
			 orderList.add(order);
		 }
		 
		 model.addAttribute("cinemaName", cinemaName);
		 model.addAttribute("movieName", movieName);
		 model.addAttribute("orderList", orderList);
		 model.addAttribute("detailTime", detailTime);
		 model.addAttribute("time", time);
		 
		 cinema.updateScreenList(time, screenList);
//		 cinemaRepository.save(cinema);
		 
		 return "order-page";
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
	

	@Override
	public void run(String... args) throws Exception {
	}
}
