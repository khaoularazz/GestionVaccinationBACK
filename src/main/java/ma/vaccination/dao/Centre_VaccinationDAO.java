package ma.vaccination.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import ma.vaccination.entities.Centre_Vaccination;
@Repository
public interface Centre_VaccinationDAO  extends MongoRepository<Centre_Vaccination, String> {
	@Query("{'ville': ?0}")
	List<Centre_Vaccination>  findALLByVille(String ville);
	@Query("{'ville': ?0, 'nom' : ?1}")
	Centre_Vaccination findByVilleNom(String ville, String nom);
	
	@Query("{'ville': ?0 }")
	 List<Centre_Vaccination >findcentresbyville(String ville);
	
	@Query("{'nom': ?0  , 'ville' : ?1}")
	Centre_Vaccination  findByNomVille(String nom , String ville);
}
