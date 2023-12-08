package com.myblog2.entity;
import lombok.Getter; 
import lombok.Setter; 
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "roles") 
public class Role { 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY) 
 private long id;
 private String name;
 @ManyToMany(mappedBy="roles")
  private Set<User> users= new HashSet<>();
}