package com.motas.blogapp.users;

import com.motas.blogapp.Common.dtos.ErrorResponse;
import com.motas.blogapp.users.DTO.CreateUserRequest;
import com.motas.blogapp.users.DTO.LoginUserRequest;
import com.motas.blogapp.users.DTO.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final ModelMapper modelMapper;

    public UsersController(UsersService usersService, ModelMapper modelMapper) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    //post mapping to signupUser
    @PostMapping("")
    ResponseEntity<UserResponse> signupUser(@RequestBody CreateUserRequest request )
    {
        //Create user
        UserEntity savedUser = usersService.createUser(request);
        URI savedUserUri = URI.create("/users/" +savedUser.getId() );
        var userResponse = modelMapper.map(savedUser, UserResponse.class);

        //CREARTE JWT TOKEN

        return ResponseEntity.created(savedUserUri).body(userResponse);
    }


    //POST MAPPING TO LOGIN USEER
    ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest request)
    {
        UserEntity savedUser = usersService.loginUser(request.getUsername(),request.getPassword());
        var userResponse = modelMapper.map(savedUser, UserResponse.class);

        return ResponseEntity.ok(userResponse);
    }

    //EXCEPTION HANDLERS
    @ExceptionHandler({
            UsersService.UserNotFoundException.class ,
            UsersService.InvalidCredentialException.class
    })
    ResponseEntity<ErrorResponse> handleUserException( Exception ex )
    {
        String message;
        HttpStatus status;

        if( ex instanceof UsersService.UserNotFoundException )
        {
            message = ex.getMessage();
            status = HttpStatus.NOT_FOUND;
        }
        else if( ex instanceof UsersService.InvalidCredentialException )
        {
            message = ex.getMessage();
            status = HttpStatus.UNAUTHORIZED;
        }
        else
        {
            message = "Something went worng";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ErrorResponse response = ErrorResponse.builder()
                .message(message)
                .build();

        return ResponseEntity.status(status).body(response);
    }




}
