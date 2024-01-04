package com.girish.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.girish.blog.entities.User;

public interface Userrepo extends JpaRepository<User,Integer> {

}
