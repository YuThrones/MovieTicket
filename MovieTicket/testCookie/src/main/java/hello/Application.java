package hello;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private CinemaRepository cinemaRepository;
	
	

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		initUser();
		initMovie();
//		initCinema();
		
		System.out.println("Init done");
	}
	
	private void initUser() {
		userRepository.deleteAll();
	}
	
	private void initMovie() {
		movieRepository.deleteAll();
		
		Movie movie0 = new Movie("Captain America3", "boom", "2016-05-12", "img/movie/s1.jpg", "英文", "美国队长", "动作");
		Movie movie1 = new Movie("s2", "boom again", "2016-06-1", "img/movie/s2.jpg", "英文", "复仇者", "动作");
		movieRepository.save(movie0);
		movieRepository.save(movie1);
	}
	
	private void initCinema() {
		Screen screen = new Screen();
		screen.setMovieName("复仇者联盟3");
		screen.setPrice(1314);
		screen.setRoom("4号厅");
		screen.setTime("21:00-230:00");
		
		Cinema cinema = new Cinema();
		cinema.addScreen("5月19日", screen);
		cinema.setCinemaName("大光明影院");
		
		cinemaRepository.save(cinema);
//		showCinema(cinema);
		
		for (Cinema oneCinema:cinemaRepository.findAll()) {
			showCinema(oneCinema);
		}
	}
	
	public void showCinema(Cinema cinema) {
		System.out.println("Cinema Name: " + cinema.getCinemaName());
		Map<String, List<Screen>> screenMap = cinema.getScreenMap();
		Set<String> timeSet = screenMap.keySet();
		for (String time:timeSet) {
			System.out.println("  Time: ");
			for (Screen screen:screenMap.get(time)) {
				System.out.println("    MovieName: " + screen.getMovieName());
				System.out.println("    Price: " + screen.getPrice());
				System.out.println("    Roome: " + screen.getRoom());
				System.out.println("    Time: " + screen.getTime());
			}
		}
	}
}
