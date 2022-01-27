package fr.emse.spring.lemeilleurcoin.api;


import fr.emse.spring.lemeilleurcoin.dao.ProductDao;
import fr.emse.spring.lemeilleurcoin.dao.UserDao;
import fr.emse.spring.lemeilleurcoin.dto.OfferDto;
import fr.emse.spring.lemeilleurcoin.dto.ProductDto;
import fr.emse.spring.lemeilleurcoin.model.Category;
import fr.emse.spring.lemeilleurcoin.model.Offer;
import fr.emse.spring.lemeilleurcoin.model.Product;
import fr.emse.spring.lemeilleurcoin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@Transactional
public class ProductController {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    /**
     *  get LIST OF PRODUCT of a specific CATEGORY and specific order if mentioned
     * @param category
     * @param order
     * @return List<ProductDto>
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/{category}")
    List<ProductDto> getProductsByCategoryAndTitle(@RequestParam (name="title",defaultValue = "")String title,@PathVariable Category category, @RequestParam(required = false,name="order",defaultValue = "desc") String order){
        if ( "ASC".equals(order) )
            return productDao.findAllByCategoryOrderByDateAsc(category).stream().map(ProductDto::new).collect(Collectors.toList());

        return productDao.findAllByCategoryOrderByDateDesc(category).stream().map(ProductDto::new).collect(Collectors.toList());
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/all")
    List<ProductDto> getProductsByTitle(@RequestParam (name="title",defaultValue = "")String title,@RequestParam (required = false,name="category")Category category ){
        if ( category == null){
            return productDao.findAllByTitle(title).stream().map(ProductDto::new).collect(Collectors.toList());
        }else{
            return productDao.findAllByTitleByCategory(category,title).stream().map(ProductDto::new).collect(Collectors.toList());
        }
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("create")
    public ProductDto create(@RequestBody ProductDto odto){
        Product product = null;
        User user = userDao.getById(odto.getUserEmail());
        if ( odto.getId() == null){
            product = productDao.save(new Product(odto.getTitle(),odto.getPrice(),odto.getDescription(),odto.getDate(),odto.getProductStatus(),odto.getCategory(),user));
        }else{
            product = productDao.getById(odto.getId());
            if ( odto.getDate() != null)
                product.setDate(odto.getDate());
            else
                product.setDate(new Date());
            product.setTitle(odto.getTitle());
            product.setPrice(odto.getPrice());
            product.setDescription(odto.getDescription());
            product.setProductStatus(odto.getProductStatus());
            product.setCategory(odto.getCategory());
        }

        return new ProductDto(product);
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/{id}")
    public Boolean delete(@RequestParam(name = "pass",defaultValue = "") String pass,@PathVariable(name = "id") Long id){
        if ( "".equals(pass)   )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Id given ");
        else{
            Product product = productDao.getById(id);
            if ( product == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No product with the given Id ");
            if ( ! product.getUser().getPassword().equals(pass) )
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password wrong");
            productDao.delete(product);
            return true;
        }
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/own/{email}")
    public List<ProductDto> getOffersByEmail(@PathVariable("email") String email){
        return productDao.findAllByUser(email).stream().map(ProductDto::new).collect(Collectors.toList());
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/product/{id}")
    public ProductDto get(@PathVariable("id") Long id){
        Product product = productDao.getById(id);
        if (  product == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found");
        return new ProductDto(product);
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("modify")
    public ProductDto modify(@RequestParam("pass")String pass ,@RequestBody ProductDto odto){
        System.out.println(pass+ " "+ odto);
        Product product = productDao.getById(odto.getId());
        if ( ! product.getUser().getPassword().equals(pass) )
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password wrong");
        product.setCategory(odto.getCategory());
        product.setPrice(odto.getPrice());
        product.setDescription(odto.getDescription());
        product.setTitle(odto.getTitle());
        return new ProductDto(product);
    }


}
