package com.example.cadastroUsuario.service;

import com.example.cadastroUsuario.dto.*;
import com.example.cadastroUsuario.model.UsuarioModel;
import com.example.cadastroUsuario.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;


    public MensagemDto adicionarUsuario(RequestDto usuarioDto) {

        MensagemDto mensagem = new MensagemDto();

        UsuarioModel usuario = new UsuarioModel();


        usuario.setNome(usuarioDto.getNome());
        usuario.setLogin(usuarioDto.getLogin());
        usuario.setSenha(usuarioDto.getSenha());

        repository.save(usuario);

        mensagem.setSucesso(true);
        mensagem.setMensagem("Cadastrado com sucesso!");


        return mensagem;

    }

    public MensagemDto autenticarUsuario(AutenticacaoDto usuarioDto) {

        MensagemDto mensagem = new MensagemDto();

        Optional<UsuarioModel> usuarioOptional = repository.findByLogin(usuarioDto.getLogin());

        if (usuarioOptional.isPresent() && usuarioDto.getSenha().equals(usuarioOptional.get().getSenha())) {
            mensagem.setSucesso(true);
            mensagem.setMensagem("Usuário autenticado com sucesso!");
            return mensagem;


        }

        return mensagem;
    }

    public MensagemDto atualizarUsuario(Long id, RequestDto usuarioAtualizar) {

        Optional<UsuarioModel> usuarioModelOptional = repository.findById(id);

        MensagemDto mensagem = new MensagemDto();

        mensagem.setMensagem("Login já existente. Tente outro.");
        mensagem.setSucesso(false);

        UsuarioModel usuarioModel = usuarioModelOptional.get();

        if (!usuarioModelOptional.get().getLogin().equals(usuarioAtualizar.getLogin())) {

            usuarioModel.setNome(usuarioAtualizar.getNome());
            usuarioModel.setLogin(usuarioAtualizar.getLogin());
            usuarioModel.setSenha(usuarioAtualizar.getSenha());

            repository.save(usuarioModel);

            mensagem.setMensagem("Usuario atualizado com sucesso!");
            mensagem.setSucesso(true);
            return mensagem;
        }


        return mensagem;
    }

    public ResponseDto buscarUsuarioPorId(Long id) {

        Optional<UsuarioModel> usuarioModelOptional = repository.findById(id);
        ResponseDto usuarioRetorno = new ResponseDto();

        if (usuarioModelOptional.isPresent()) {
            usuarioRetorno.setId(usuarioModelOptional.get().getId());
            usuarioRetorno.setNome(usuarioModelOptional.get().getNome());
            usuarioRetorno.setLogin(usuarioModelOptional.get().getLogin());


        }

        return usuarioRetorno;
    }


    public List<ConsultaUsuarioDto> listarUsuarios() {

        List<ConsultaUsuarioDto> listaUsuariosDtos = new ArrayList<>();

        List<UsuarioModel> listaUsuarioModel = repository.findAll();

        for (UsuarioModel usuarioModel : listaUsuarioModel) {

            ConsultaUsuarioDto usuario = new ConsultaUsuarioDto();

            usuario.setId(usuarioModel.getId());
            usuario.setNome(usuarioModel.getNome());
            usuario.setLogin(usuarioModel.getLogin());


            listaUsuariosDtos.add(usuario);
        }
            return listaUsuariosDtos;

    }

    public MensagemDto removerUsuario(Long id) {

        List<UsuarioModel> listaUsuarioModel = repository.findAll();

        MensagemDto mensagem = new MensagemDto();
        mensagem.setMensagem("Erro ao excluir");
        mensagem.setSucesso(false);

        UsuarioModel usuarioModel = new UsuarioModel();


        for (UsuarioModel usuario : listaUsuarioModel) {
            if (usuario.getId() == id) {
                usuarioModel = usuario;
            }
        }

        if (usuarioModel.getId() != 0) {
            listaUsuarioModel.remove(usuarioModel);
            mensagem.setMensagem("Excluido usuário com sucesso!");
            mensagem.setSucesso(true);
        }

        return mensagem;
    }


}
