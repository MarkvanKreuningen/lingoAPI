package com.project.lingo.presentation.dto;

import com.project.lingo.validation.ValidEmail;
import com.project.lingo.validation.ValidWachtwoord;
import com.project.lingo.validation.WachtwoordMatches;
import com.sun.istack.NotNull;
import jakarta.validation.constraints.Size;

@WachtwoordMatches
public class UserDto {
    @NotNull
    @Size(min = 1, message = "{Size.userDto.username}")
    public String username;

    @ValidWachtwoord
    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @Size(min = 1, message = "{Size.userDto.email}")
    private String email;

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(final String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("UserDto [username=")
                .append(username)
                .append(", email=")
                .append(email)
                .append(", role=")
                .append(role).append("]");
        return builder.toString();
    }
}
