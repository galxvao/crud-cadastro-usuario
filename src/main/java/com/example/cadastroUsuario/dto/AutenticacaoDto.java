package com.example.cadastroUsuario.dto;

public class AutenticacaoDto {
    private String login;
    private String senha;

    public AutenticacaoDto() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
