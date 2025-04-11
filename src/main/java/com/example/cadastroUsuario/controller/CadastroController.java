package com.example.cadastroUsuario.controller;

import com.example.cadastroUsuario.dto.MensagemDto;
import com.example.cadastroUsuario.dto.RequestDto;
import com.example.cadastroUsuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    UsuarioService service;


    @GetMapping
    public String obterCadastro(Model model){

        RequestDto cadastroDto = new RequestDto();
        model.addAttribute("cadastroDto", cadastroDto);
        return "cadastro";

    }

    @PostMapping
    public String realizarCadastro(@ModelAttribute("cadastroDto") RequestDto cadastroDto){
        MensagemDto mensagem = service.adicionarUsuario(cadastroDto);

        if(mensagem.isSucesso()){
            return "redirect:/cadastro?sucesso";
        }
        return "redirect:/cadastro?erro";
    }


}
