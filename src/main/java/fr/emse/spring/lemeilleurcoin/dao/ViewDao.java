package fr.emse.spring.lemeilleurcoin.dao;

import fr.emse.spring.lemeilleurcoin.dto.ViewDto;
import fr.emse.spring.lemeilleurcoin.model.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ViewDao extends JpaRepository<View, Long> {
    @Query("select v from View v where v.viewedUser.email = :email")
    List<View> findByViewedUser(@Param("email")String email);
}
