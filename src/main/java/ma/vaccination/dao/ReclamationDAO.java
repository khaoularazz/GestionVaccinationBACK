package ma.vaccination.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import ma.vaccination.entities.Client;
import ma.vaccination.entities.Reclamation;

public interface ReclamationDAO extends MongoRepository<Reclamation, String>{
	@Query("{'id_client': ?0}")
	Reclamation findByclient(Client id_client);
}
