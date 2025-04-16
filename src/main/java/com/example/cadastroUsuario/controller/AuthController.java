package com.example.cadastroUsuario.controller;

import com.example.cadastroUsuario.dto.AutenticacaoDto;
import com.example.cadastroUsuario.dto.MensagemDto;
import com.example.cadastroUsuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/login")
public class AuthController {

    @Autowired
    UsuarioService service;

    @GetMapping
    public String obterLogin(Model model){

        AutenticacaoDto autenticacaoDto = new AutenticacaoDto();
        model.addAttribute("autenticacaoDto", autenticacaoDto);

        return "login";
    }

    @PostMapping
    public String autenticarUsuario(@ModelAttribute("autenticacaoDto") AutenticacaoDto autenticacaoDto){

        System.out.println(autenticacaoDto.getLogin() + " " + autenticacaoDto.getSenha());

        MensagemDto mensagemDto = new MensagemDto();

        if(mensagemDto.isSucesso()) {
            return "redirect:/home?sucesso";
        }
        return "redirect:/login?erro";
    }






}
