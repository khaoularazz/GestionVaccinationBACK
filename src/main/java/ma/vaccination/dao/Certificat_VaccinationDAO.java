package ma.vaccination.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import ma.vaccination.entities.Centre_Vaccination;
import ma.vaccination.entities.Certificat_Vaccination;
import ma.vaccination.entities.Client;

public interface Certificat_VaccinationDAO  extends MongoRepository<Certificat_Vaccination, String>  {
	@Query("{'client': ?0}")
	Certificat_Vaccination  findBy(Client client);
}
