package com.witnes.SpringSecEx.repo;

import com.witnes.SpringSecEx.model.Student;
import com.witnes.SpringSecEx.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {

    Users findByUsername(String username);


}
