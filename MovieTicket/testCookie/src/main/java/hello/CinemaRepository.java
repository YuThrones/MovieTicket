package hello;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CinemaRepository  extends MongoRepository<Cinema, String>{
	public List<Cinema> findByCinemaName(String name);
}
