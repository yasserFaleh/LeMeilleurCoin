package fr.emse.spring.lemeilleurcoin.dao;

import fr.emse.spring.lemeilleurcoin.model.Category;
import fr.emse.spring.lemeilleurcoin.model.Offer;
import fr.emse.spring.lemeilleurcoin.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferDao extends JpaRepository<Offer, Long> {
    List<Offer> findAllByCategoryOrderByDateDesc(Category category);
    List<Offer> findAllByCategoryOrderByDateAsc(Category category);

    @Query("Select o FROM Offer o where o.category = :category and lower(o.title)  like concat('%',lower(:title),'%') order by o.date desc ")
    List<Offer> findAllByTitleByCategory(@Param("category") Category category, @Param("title") String title);

    @Query("Select o FROM Offer o where   lower(o.title)  like concat('%',lower(:title),'%') order by o.date desc ")
    List<Offer> findAllByTitle( @Param("title") String title);

    @Query("Select o FROM Offer o where   o.user.email= :email order by o.date desc ")
    List<Offer> findAllByUser(@Param("email") String email);
}
