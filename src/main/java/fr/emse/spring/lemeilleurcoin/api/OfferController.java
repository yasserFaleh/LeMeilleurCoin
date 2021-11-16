package fr.emse.spring.lemeilleurcoin.api;


import fr.emse.spring.lemeilleurcoin.dao.OfferDao;
import fr.emse.spring.lemeilleurcoin.dao.UserDao;
import fr.emse.spring.lemeilleurcoin.dto.OfferDto;
import fr.emse.spring.lemeilleurcoin.dto.ProductDto;
import fr.emse.spring.lemeilleurcoin.model.Category;
import fr.emse.spring.lemeilleurcoin.model.Offer;
import fr.emse.spring.lemeilleurcoin.model.Product;
import fr.emse.spring.lemeilleurcoin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/offers")
@Transactional
public class OfferController {
    @Autowired
    private OfferDao offerDao;

    @Autowired
    private UserDao userDao;

    /**
     *  get LIST OF OFFER of a specific CATEGORY and specific order if mentioned
     * @param category
     * @param order
     * @return List<OfferDto>
     */
    @GetMapping("/{category}")
    List<OfferDto> getProductsByCategory(@PathVariable Category category, @RequestParam(required = false,name="order",defaultValue = "desc") String order){
        if ( "ASC".equals(order) )
            return offerDao.findAllByCategoryOrderByDateAsc(category).stream().map(OfferDto::new).collect(Collectors.toList());

        return offerDao.findAllByCategoryOrderByDateDesc(category).stream().map(OfferDto::new).collect(Collectors.toList());
    }

    @GetMapping("/all")
    List<OfferDto> getProductsByTitle(@RequestParam (name="title",defaultValue = "")String title, @RequestParam (required = false,name="category")Category category ){
        if ( category == null){
            return offerDao.findAllByTitle(title).stream().map(OfferDto::new).collect(Collectors.toList());
        }else{
            return offerDao.findAllByTitleByCategory(category,title).stream().map(OfferDto::new).collect(Collectors.toList());
        }
    }

    @PostMapping("create")
    public OfferDto create(@RequestBody OfferDto odto){
        Offer offer = null;
        User user = userDao.getById(odto.getUserEmail());
        if ( odto.getId() == null){
            offer = offerDao.save(new Offer(odto.getTitle(),odto.getPrice(),odto.getDescription(),odto.getDate(),odto.getOfferStatus(),odto.getCategory(),user));
        }else{
            offer = offerDao.getById(odto.getId());
            if ( odto.getDate() != null)
                offer.setDate(odto.getDate());
            else
                offer.setDate(new Date());
            offer.setTitle(odto.getTitle());
            offer.setPrice(odto.getPrice());
            offer.setDescription(odto.getDescription());
            offer.setOfferStatus(odto.getOfferStatus());
            offer.setCategory(odto.getCategory());
        }

        return new OfferDto(offer);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@RequestParam(name = "pass",defaultValue = "") String pass,@PathVariable(name = "id") Long id){
        if ( "".equals(pass)   )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Id given ");
        else{
            Offer offer = offerDao.getById(id);
            if ( offer == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No offer with the given Id ");
            if ( ! offer.getUser().getPassword().equals(pass) )
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password wrong");
            offerDao.delete(offer);
            return true;
        }
    }

    @GetMapping("/own/{email}")
    public List<OfferDto> getOffersByEmail(@PathVariable("email") String email){
        return offerDao.findAllByUser(email).stream().map(OfferDto::new).collect(Collectors.toList());
    }

    @GetMapping("/offer/{id}")
    public OfferDto get(@PathVariable("id") Long id){
        Offer offer = offerDao.getById(id);
        if (  offer == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found");
        return new OfferDto(offer);
    }

    @PostMapping("/modify")
    public OfferDto modify(@RequestParam("pass")String pass ,@RequestBody OfferDto odto){
        Offer offer = offerDao.getById(odto.getId());
        if ( ! offer.getUser().getPassword().equals(pass) )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password wrong");
        offer.setCategory(odto.getCategory());
        offer.setPrice(odto.getPrice());
        offer.setDescription(odto.getDescription());
        offer.setTitle(odto.getTitle());
        return new OfferDto(offer);
    }
}
