package configuration.management.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import configuration.management.model.EProcJPA;

public interface EProcRepository extends CrudRepository<EProcJPA, Long> {

		public EProcJPA findByName(@Param("name") String name);
}
