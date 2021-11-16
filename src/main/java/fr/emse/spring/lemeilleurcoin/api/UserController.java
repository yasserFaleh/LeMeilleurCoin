package fr.emse.spring.lemeilleurcoin.api;


import fr.emse.spring.lemeilleurcoin.dao.UserDao;
import fr.emse.spring.lemeilleurcoin.dto.UserDto;
import fr.emse.spring.lemeilleurcoin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/users")
@Transactional
public class UserController {
    @Autowired
    private UserDao userDao;

    /**
     *                      Register a new User
     * @param email
     * @param fullName
     * @param password
     * @param phoneNum
     * @return true if the registration succeed else false
     */
    @GetMapping ("/register")
    public Boolean register(@RequestParam(name = "email", defaultValue = "") String email,@RequestParam(name = "fullName", defaultValue = "") String fullName,@RequestParam(name = "pass", defaultValue = "") String password,@RequestParam(name = "phoneNum", defaultValue = "") String phoneNum ){
        // check params existing
        if ( "".equals(email) || "".equals(password) || "".equals(fullName) || "".equals(phoneNum)  )
            return false;

        //check email doesn't exist
        if ( ! userDao.findById(email).isEmpty())
            return false;

        // check criteria of some fields then save the new user
        if ( password.length() > 6 && phoneNum.length() == 10 ) {
            userDao.save(new User(email, password, fullName, phoneNum));
            return true;
        }
        return false;
    }
    @GetMapping("/login")
    public UserDto get(@RequestParam(name = "email",defaultValue = "") String email, @RequestParam(name = "pass",defaultValue = "") String password){
        if ( "".equals(email) || "".equals(password)){
            return new UserDto();
        }

        UserDto usdto = userDao.findById(email).map(UserDto::new).orElse(new UserDto());
        if (usdto.getEmail() != null && usdto.getPassword() != null && usdto.getEmail().equals(email) && usdto.getPassword().equals(password))
            return usdto;

        return new UserDto();
    }

    /**
     *                  Check if an email  already exists !
     * @param email
     *        Response code = 200 when the mail doesn't exist
     *           else  code = 400 when it exists
     */
    @GetMapping("/check/{email}")
    public String check(@PathVariable String email){
        if ( userDao.findById(email).isEmpty() )
            throw new ResponseStatusException(HttpStatus.ACCEPTED, "Mail doesn't exist");
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mail already used");

    }
    @GetMapping("/{email}")
    public UserDto get(@PathVariable String email){
        UserDto usdto = userDao.findById(email).map(UserDto::new).orElse(new UserDto());
        usdto.setPassword(null);
        return usdto;
    }

    @PostMapping("/modify")
    public UserDto modify(@RequestParam(name = "pass",defaultValue = "") String oldPass,@RequestBody UserDto udto){
        System.out.println(oldPass+" "+udto.getEmail()+" "+udto.getPassword()+" "+ udto.getFullName().length()+" "+udto.getPhoneNum().length());
        if (oldPass.equals("") || udto == null || udto.getEmail() == null || udto.getPassword() == null || udto.getPassword().length() < 8 || udto.getFullName() == null || udto.getFullName().length() < 5 || udto.getPhoneNum() == null || udto.getPhoneNum().length() != 10 ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Give all new info about user and the old pass as a param");
        }else {

            User user = userDao.getById(udto.getEmail());
            if ( user.getPassword().equals(oldPass)){
                user.setPassword(udto.getPassword());
                user.setFullName(udto.getFullName());
                user.setPhoneNum(udto.getPhoneNum());
                return udto;
            }else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Old password wrong ");


        }
    }


}
