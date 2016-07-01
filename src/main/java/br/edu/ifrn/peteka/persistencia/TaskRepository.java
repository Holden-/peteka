package br.edu.ifrn.peteka.persistencia;

import br.edu.ifrn.peteka.dominio.Status;
import br.edu.ifrn.peteka.dominio.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * Using Query By Example
 */
public interface TaskRepository extends CrudRepository<Task, Long>, 
        QueryByExampleExecutor<Task>, TaskRepositoryCustom {
    
    //Query Method
    void deleteByStatus(Status status);
    
    Task findByTitleAndDescription(String title, String description);
    
}
