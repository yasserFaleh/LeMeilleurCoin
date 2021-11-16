package fr.emse.spring.lemeilleurcoin.api;



import fr.emse.spring.lemeilleurcoin.dao.ViewDao;
import fr.emse.spring.lemeilleurcoin.dto.UserDto;
import fr.emse.spring.lemeilleurcoin.dto.ViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/views")
@Transactional
public class ViewController {
    @Autowired
    private ViewDao viewDao;

    @GetMapping("/{email}")
    public List<ViewDto> getAllUserViews(@PathVariable String email){
        return viewDao.findByViewedUser(email).stream().map(ViewDto::new).collect(Collectors.toList());
    }


}
