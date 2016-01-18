package common.repository;

import org.springframework.data.repository.query.Param;

public interface RepositoryCommon<T> {

    public T findByName(@Param("name") String name);

}
