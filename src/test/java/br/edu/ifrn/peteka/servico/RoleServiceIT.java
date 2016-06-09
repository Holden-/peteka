/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.peteka.servico;

import br.edu.ifrn.peteka.PetekaApplication;
import br.edu.ifrn.peteka.dominio.Role;
import javax.inject.Inject;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author joab
 */
@SpringApplicationConfiguration(classes = PetekaApplication.class)
@WebAppConfiguration
@Test
public class RoleServiceIT extends AbstractTestNGSpringContextTests {
    
    @Inject
    private RoleService roleService;
    
    private final String ROLE_TITLE = "title";
    
    
    @BeforeMethod
    void deleteAll()
    {
        roleService.deleteAll();
        assertThat(roleService.findAll()).isEmpty();
    }
    
    public void testServiceIsNotNull(){
        assertThat(roleService).isNotNull();
    }
    
    public void testSaveOne(){
        // Creates the test environment
        Role role = Role.builder().title(this.ROLE_TITLE).build();
        
        // Saves
        this.roleService.save(role);
        
        // Verifies if saved
        assertThat(this.roleService.findAll().iterator().next()).isEqualTo(role);
        
    }
    
    
    public void testDeleteOne(){
        // Creates the test environment
        Role role = Role.builder().title(this.ROLE_TITLE).build();
        
        // Saves
        this.roleService.save(role);
        
        // Deletes
        this.roleService.delete(role);
        
        //Test if deleted
        assertThat(this.roleService.findAll().iterator().hasNext()).isFalse();
    }
    
}