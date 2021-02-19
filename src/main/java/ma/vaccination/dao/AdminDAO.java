package ma.vaccination.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import ma.vaccination.entities.Admin;
@CrossOrigin
@Repository
public interface AdminDAO extends MongoRepository<Admin, String>  {
	@Query("{'email': ?0, 'password' : ?1}")
	Admin findByEmailPassword(String email , String password);

}
