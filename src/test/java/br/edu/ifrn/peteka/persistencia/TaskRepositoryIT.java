/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.peteka.persistencia;

import br.edu.ifrn.peteka.PetekaApplication;
import br.edu.ifrn.peteka.dominio.Project;
import br.edu.ifrn.peteka.dominio.Status;
import br.edu.ifrn.peteka.dominio.Task;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Example;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author joab
 */
@SpringApplicationConfiguration(classes = PetekaApplication.class)
@WebAppConfiguration
@Test(groups = "task")
public class TaskRepositoryIT extends AbstractTestNGSpringContextTests  {
    
    @Inject
    private TaskRepository taskRepository;
    
    private final String TASK_TITLE = "title";
    private final String TASK_DESCRIPTION = "D1";
    
    @BeforeMethod
    void deleteAll(){
        taskRepository.deleteAll();
        assertThat(taskRepository.findAll()).isEmpty();
    }

    public void testRepositoryIsNotNull(){
        assertThat(taskRepository).isNotNull();
    }
    
    public void testSaveOne(){
       // Creates the test environment
        Task task = Task.builder()
                .title(this.TASK_TITLE)
                .description(this.TASK_DESCRIPTION).build();
        
        // Saves
        this.taskRepository.save(task);
        
        // Verifies if saved
        assertThat(taskRepository.findAll().iterator().next()).isEqualTo(task);
        
    }
    
    
    public void testDeleteOne(){
        // Creates the test environment
        Task task = Task.builder()
                .title(this.TASK_TITLE)
                .description(this.TASK_DESCRIPTION).build();
        this.taskRepository.save(task);
        
        // Deletes
        this.taskRepository.delete(task);
        
        //Test if deleted
        assertThat(taskRepository.findOne(task.getId())).isNull();
    }
    
    // query by example, test do not apply
    /*public void testDeleteByStatus(){
    
        Status status = Status.builder().label("label").build();
        
        Task task = Task.builder()
                .title(this.TASK_TITLE)
                .description(this.TASK_DESCRIPTION)
                .status(status).build();
        this.taskRepository.save(task);
        assertThat(this.taskRepository.findAll()).isNotEmpty();
        
        this.taskRepository.deleteByStatus(status);
        
        assertThat(this.taskRepository.findAll()).isNotEmpty();
        
    }*/
    public void findAllByExample () {
        // cria o ambiente de teste
        Task task = Task.builder()
                .title(this.TASK_TITLE)
                .description(this.TASK_DESCRIPTION)
                .status(
                        Status.builder().label("label").build()
                ).build();
        
        this.taskRepository.save(task);
        
        Task taskExample = Task.builder()
                .title(this.TASK_TITLE)
                .description(this.TASK_DESCRIPTION)
                .status(
                        Status.builder().label("label").build()
                ).build();
        
        assertThat(this.taskRepository.findAll(Example.of(taskExample)).iterator().next())
            .isEqualTo(task);
    }
    
    public void testGetAllTasksForProject() {
        Project project = Project.builder()
                .title("Project title")
                .description("Description")
                .build();
        Task task = Task.builder()
                .title(this.TASK_TITLE)
                .description(this.TASK_DESCRIPTION)
                .project(project)
                .build();
        
        assertThat(taskRepository.getAllTasksForProject(project)
                .contains(task));
    }
    
    public void testGetAllTasksForProjectOfStatus() {
        // Creates the test environment
        Project project = Project.builder()
                .title("Project title")
                .description("Description")
                .build();
        Status status = Status.builder()
                .label("label")
                .build();
        Task task = Task.builder()
                .title(this.TASK_TITLE)
                .description(this.TASK_DESCRIPTION)
                .project(project)
                .build();
        
        assertThat(taskRepository.getAllTasksForProjectOfStatus(project, status)
                .contains(task));
    }
    
}