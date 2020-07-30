package io.nabiullin.ppmtool.web;

import io.nabiullin.ppmtool.domain.User;
import io.nabiullin.ppmtool.services.MapValidationErrorService;
import io.nabiullin.ppmtool.services.UserService;
import io.nabiullin.ppmtool.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result){
        //validate password's match
        userValidator.validate(user,result);


        ResponseEntity<?>errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap !=null) return errorMap;

        User newUser = userService.saveUser(user);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
}
