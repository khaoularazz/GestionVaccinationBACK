package ma.vaccination.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import ma.vaccination.entities.Admin;
import ma.vaccination.entities.Rendez_vous;
@CrossOrigin
@Repository
public interface Rendez_vousDAO extends MongoRepository<Rendez_vous, String> {
	@Query("{'valide': ?0, 'dose' : ?1, 'date' : ?2}")
	List<Rendez_vous> findBy(int valide , int dose , String date);
	@Query("{'valide': ?0, 'dose' : ?1,}")
	List<Rendez_vous> findBy(int valide , int dose );
}
