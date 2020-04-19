package kz.muminov.iitu.library.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private kz.muminov.iitu.library.enums.Role name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role(kz.muminov.iitu.library.enums.Role name) {
        this.name = name;
    }

    public Role(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public kz.muminov.iitu.library.enums.Role getName() {
        return name;
    }

    public void setName(kz.muminov.iitu.library.enums.Role name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return name.toString();
    }

}
