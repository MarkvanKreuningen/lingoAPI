package com.project.lingo.Presentation.dto;

import com.project.lingo.validation.ValidWachtwoord;

public class WachtwoordDto {

    private  String token;

    @ValidWachtwoord
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
