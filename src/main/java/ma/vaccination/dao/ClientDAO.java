package ma.vaccination.dao;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import ma.vaccination.entities.Client;
import ma.vaccination.entities.Reclamation;
@CrossOrigin
@Repository
public interface ClientDAO extends MongoRepository<Client, String>{
	
	@Query("{'email': ?0, 'password' : ?1}")
	Client findByEmailPassword(String email , String password);
	@Query("{'email': ?0}")
	Client findByEmail(String email);
	@Query("{'ville': ?0}")
	List<Client> findByVille (String ville ) ;

	
	
	
}
