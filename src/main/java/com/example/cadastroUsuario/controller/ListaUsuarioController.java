package com.example.cadastroUsuario.controller;

import com.example.cadastroUsuario.dto.ConsultaUsuarioDto;
import com.example.cadastroUsuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/lista")
public class ListaUsuarioController {

    @Autowired
    UsuarioService service;

    @GetMapping
    public String obterLista(Model model){

        List<ConsultaUsuarioDto> listaUsuarioDto = service.listarUsuarios();
        model.addAttribute("consultaUsuarioDto", listaUsuarioDto);

        return "lista";
    }
}
