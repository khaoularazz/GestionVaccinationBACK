package ma.vaccination.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import ma.vaccination.entities.Effet_Secondaire;

public interface Effet_SecondaireDAO extends MongoRepository<Effet_Secondaire, String> {

}
