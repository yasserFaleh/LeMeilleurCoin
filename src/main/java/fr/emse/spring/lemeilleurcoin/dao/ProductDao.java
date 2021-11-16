package fr.emse.spring.lemeilleurcoin.dao;

import fr.emse.spring.lemeilleurcoin.model.Category;
import fr.emse.spring.lemeilleurcoin.model.Offer;
import fr.emse.spring.lemeilleurcoin.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryOrderByDateDesc(Category category);
    List<Product> findAllByCategoryOrderByDateAsc(Category category);

    @Query("Select p FROM Product p where p.category = :category and lower(p.title)  like concat('%',lower(:title),'%') order by p.date desc ")
    List<Product> findAllByTitleByCategory(@Param("category") Category category, @Param("title") String title);

    @Query("Select p FROM Product p where   lower(p.title)  like concat('%',lower(:title),'%') order by p.date desc ")
    List<Product> findAllByTitle( @Param("title") String title);

    @Query("Select o FROM Product o where   o.user.email= :email order by o.date desc ")
    List<Product> findAllByUser(@Param("email") String email);
}
