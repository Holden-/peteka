/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.peteka.persistencia;

import br.edu.ifrn.peteka.dominio.Project;
import br.edu.ifrn.peteka.dominio.User;
import java.util.Set;

/**
 *
 * @author duartemac
 */
public interface ProjectRepositoryCustom {
    Set<Project> getAllProjectsOfUser(User u);
}