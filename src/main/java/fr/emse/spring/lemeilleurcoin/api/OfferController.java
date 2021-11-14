package fr.emse.spring.lemeilleurcoin.api;


import fr.emse.spring.lemeilleurcoin.dao.UserDao;
import fr.emse.spring.lemeilleurcoin.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/users")
@Transactional
public class UserController {
    @Autowired
    private UserDao userDao;

    @PostMapping
    public UserDto inscription(@RequestBody UserDto udt){
        UserDto userDto = null;
        //TO-DO
        return userDto;
    }
    @GetMapping("/{email}")
    public UserDto get(@PathVariable String email){
        return userDao.findById(email).map(UserDto::new).orElse(new UserDto());
    }


}
