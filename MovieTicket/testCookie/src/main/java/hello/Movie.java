package hello;

import org.springframework.data.annotation.Id;

public class Movie {
	@Id
	private String id;
	
	private String movieName;
	private String movieDescription;
	private String date;
	private String imgUrl;
	private String language;
	private String actor;
	private String type;
	
	public Movie() {};
	
	
	public Movie(String movieName, String movieDescription,
			String date, String url, String language, String actor, String type) {
		this.movieName = movieName;
		this.movieDescription = movieDescription;
		this.date = date;
		this.imgUrl = url;
		this.language = language;
		this.actor = actor;
		this.type = type;
	}
	
	public String getMovieDescription() {
		return this.movieDescription;
	}
	
	public String getMovieName() {
		return this.movieName;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getImgUrl() {
		return this.imgUrl;
	}
	
	public String getActor() {
		return this.actor;
	}
	
	public String getLanguage() {
		return this.language;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public void setActor(String actor) {
		this.actor = actor;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return String.format("Movie:[\nName:%s\nDescription:%s\nDate:%s]", movieName, movieDescription, date);
	}
}
