/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrn.peteka.dominio;

import java.io.Serializable;
import java.util.Set;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NonNull;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "id")
@Builder
@Entity
@SequenceGenerator(sequenceName = "seq_project", name = "ID_SEQUENCE", allocationSize = 1)
public class Project implements Serializable, Comparable<Project> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "ID_SEQUENCE")
    private Long id;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private Set<Task> tasks;

    @NonNull
    @Column(nullable = false)
    private String title;

    private String description;
    
    @Override
    public int compareTo(Project o) {
        int result = title.compareTo(o.title);
        if(result == 0){
            result = description.compareTo(o.description);
        }
        return result;
    }

}
