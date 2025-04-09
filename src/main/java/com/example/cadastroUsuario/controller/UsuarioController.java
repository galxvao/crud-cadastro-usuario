package com.example.cadastroUsuario.controller;

import com.example.cadastroUsuario.dto.*;
import com.example.cadastroUsuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/crud")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @PostMapping("/usuario")
    public ResponseEntity<MensagemDto> cadastrar(@RequestBody RequestDto usuarioDto) {

        MensagemDto mensagem = service.adicionarUsuario(usuarioDto);

        if (mensagem.isSucesso()) {
            return ResponseEntity.ok().body(mensagem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }

    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<MensagemDto> atualizar(@RequestBody RequestDto usuario, @PathVariable Long id) {
        MensagemDto mensagem = service.atualizarUsuario(id, usuario);

        if (mensagem.isSucesso()) {
            return ResponseEntity.ok().body(mensagem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<ResponseDto> obter(@PathVariable Long id) {

        ResponseDto resposta = service.buscarUsuarioPorId(id);
        return ResponseEntity.ok().body(resposta);
    }

    @PostMapping("/login")
    public ResponseEntity<MensagemDto> autenticar (@RequestBody AutenticacaoDto usuarioDto){

        MensagemDto mensagem = service.autenticarUsuario(usuarioDto);



        if (mensagem.isSucesso()){
            return ResponseEntity.ok().body(mensagem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }

    }




    @GetMapping("/usuarios")
    public ResponseEntity<List<ListaUsuariosDto>> obterLista() {

        List<ListaUsuariosDto> lista = service.listarUsuarios();
        return ResponseEntity.ok().body(lista);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<MensagemDto> excluir(@PathVariable Long id) {

        MensagemDto mensagem = service.removerUsuario(id);

        if (mensagem.isSucesso()) {
            return ResponseEntity.ok().body(mensagem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }

    }
}
