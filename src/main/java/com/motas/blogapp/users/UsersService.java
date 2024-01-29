package com.motas.blogapp.users;

import com.motas.blogapp.users.DTO.CreateUserRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final ModelMapper modelMapper;
//    private final PasswordEncoder passwordEncoder;//TO BE ADDED
    public UsersService(UsersRepository usersRepository, ModelMapper modelMapper) {
        this.usersRepository = usersRepository;
        this.modelMapper = modelMapper;
    }

    public  UserEntity createUser(CreateUserRequest u)
    {
        //creating a new user
        UserEntity newUser = modelMapper.map(u , UserEntity.class);
        //PASSWORD TO BE ENCODED

        return usersRepository.save(newUser);//Saving the new user
    }

    public UserEntity getUser( Long userId )
    {
        //User Repository gets the job done here , if not foudn throw exception
        return usersRepository.findById(userId).orElseThrow( () -> new UserNotFoundException(userId)  );

    }

    public UserEntity getUser(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    public UserEntity loginUser( String username , String password )
    {
        var user = usersRepository.findByUsername(username).orElseThrow(() -> new InvalidCredentialException());
        //MATCH PASSWORD AND ENCODE IT


        return user;
    }


    public static class UserNotFoundException extends IllegalArgumentException
    {
        public UserNotFoundException( Long userId )
        {
            super( "User with id : " + userId + " not found");
        }
        public UserNotFoundException( String username )
        {
            super( "User with useranme : " + username  + " not found");
        }


    }

    public static class InvalidCredentialException extends IllegalArgumentException
    {
        public InvalidCredentialException()
        {
            super("Invalid useranme / password combination");
        }

    }








}
