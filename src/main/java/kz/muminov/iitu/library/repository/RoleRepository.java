package kz.muminov.iitu.library.repository;

import kz.muminov.iitu.library.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(kz.muminov.iitu.library.enums.Role name);

}
