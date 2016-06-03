package hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;

public class Cinema {
	@Id
	private String id;
	private String cinemaName;
	private Map<String, List<Screen>> screenMap = new HashMap<String, List<Screen>>();
	
	public String getCinemaName() {
		return cinemaName;
	}
	
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	
	public List<Screen> getScreenListByTime(String time) {
		return screenMap.get(time);
	}
	
	public Map<String, List<Screen>> getScreenMap() {
		return this.screenMap;
	}
//	public void addScreenList(String time, List<Screen> screenList) {
//		screenMap.put(time, screenList);
//	}
	
	public void addScreen(String time, Screen screen) {
		List<Screen> screenList;
		if (screenMap.get(time) == null) screenList = new ArrayList<Screen>();
		else screenList = screenMap.get(time);
		screenList.add(screen);
		screenMap.put(time, screenList);
	}
}
