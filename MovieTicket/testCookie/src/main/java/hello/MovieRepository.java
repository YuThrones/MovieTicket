package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String>{
	
	
	public List<Movie> findByMovieName(String name);
	
	public List<Movie> findByMovieDescription(String description);
	
	public List<Movie> findByDate(String date);

}
