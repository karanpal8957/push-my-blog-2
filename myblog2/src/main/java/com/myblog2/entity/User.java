package com.myblog2.entity;
import lombok.Data; 
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
@Entity
@Table(name = "users", uniqueConstraints = {
 @UniqueConstraint(columnNames = {"username"}),
 @UniqueConstraint(columnNames = {"email"})
})
public class User { 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY) 
 private Long id;
 private String name; 
 private String username; 
 private String email; 
 private String password; 
 @ManyToMany(fetch = FetchType.EAGER)
 @JoinTable(name = "user_roles", 
 joinColumns = @JoinColumn(name = "user_id"),
 inverseJoinColumns = @JoinColumn(name = "role_id"))
 private Set<Role>roles= new HashSet<>();
}