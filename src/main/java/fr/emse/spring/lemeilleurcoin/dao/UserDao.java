package fr.emse.spring.lemeilleurcoin.dao;

import fr.emse.spring.lemeilleurcoin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String> {
}
