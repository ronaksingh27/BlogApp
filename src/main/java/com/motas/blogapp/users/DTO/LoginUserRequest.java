package com.motas.blogapp.users.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class LoginUserRequest
{
    @NonNull
    String username;

    @NonNull
    String password;
}
