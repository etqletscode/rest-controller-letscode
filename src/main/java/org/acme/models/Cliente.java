package org.acme.models;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.acme.enums.PerfilEnum;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Cliente {
    private Long id;

    @NotBlank(message = "Nome obrigatório")
    private String nome;

    @NotBlank(message = "CPF nao pode ser vazio")
    private String cpf;

    @NotBlank(message = "Email nao pode ser vazio")
    @Email(message = "Email invalido")
    private String email;

    @JsonIgnoreProperties(allowGetters = true)
    private String senha;

    @Enumerated(EnumType.STRING)
    private PerfilEnum perfil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

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

    public PerfilEnum getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilEnum perfil) {
        this.perfil = perfil;
    }
}
