package org.acme.models;

import javax.validation.constraints.NotNull;

public class Login {
    @NotNull(message = "Email nao pode ser nulos")
    private String email;

    @NotNull(message = "Senha nao pode ser nula")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
