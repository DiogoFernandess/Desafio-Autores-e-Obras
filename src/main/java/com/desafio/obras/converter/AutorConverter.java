package com.desafio.obras.converter;

import com.desafio.obras.dto.AutorDTO;
import com.desafio.obras.dto.EnderecoDTO;
import com.desafio.obras.dto.TelefoneDTO;
import com.desafio.obras.entity.Autor;
import com.desafio.obras.entity.Endereco;
import com.desafio.obras.entity.Telefone;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutorConverter {
    public Autor paraAutor(AutorDTO autorDTO){
        return Autor.builder()
                .nome(autorDTO.getNome())
                .email(autorDTO.getEmail())
                .senha(autorDTO.getSenha())
                .enderecos(autorDTO.getEnderecos() !=null ?
                        paraListaEndereco(autorDTO.getEnderecos()) : null)
                .telefones(autorDTO.getTelefones() !=null ?
                        paraListaTelefone(autorDTO.getTelefones()) : null)
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDTOS){
        return enderecoDTOS.stream().map(this::paraEndereco).toList();

    }
    public Endereco paraEndereco(EnderecoDTO enderecoDTO){
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .build();
    }

    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefone).toList();
    }
    public Telefone paraTelefone (TelefoneDTO telefoneDTO){
        return Telefone.builder()
                .id(telefoneDTO.getId())
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }


    //Parâmetros utilizando entity's
    public AutorDTO paraAutorDTO(Autor autorDTO){
        return AutorDTO.builder()
                .nome(autorDTO.getNome())
                .email(autorDTO.getEmail())
                .senha(autorDTO.getSenha())
                .enderecos(autorDTO.getEnderecos() != null ?
                        paraListaEnderecoDTO(autorDTO.getEnderecos()) : null)
                .telefones(autorDTO.getTelefones() !=null ?
                        paraListaTelefoneDTO(autorDTO.getTelefones()) : null)
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecoDTOS){
        return enderecoDTOS.stream().map(this::paraEnderecoDTO).toList();
    }


    public EnderecoDTO paraEnderecoDTO(Endereco endereco){
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .cidade(endereco.getCidade())
                .complemento(endereco.getComplemento())
                .estado(endereco.getEstado())
                .cep(endereco.getCep())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefoneDTOS){
        return telefoneDTOS.stream().map(this::paraTelefoneDTO).toList();
    }

    //Para endereço entity
    public TelefoneDTO paraTelefoneDTO(Telefone telefone){
        return TelefoneDTO.builder()
                .id(telefone.getId())
                .numero(telefone.getNumero())
                .ddd(telefone.getDdd())
                .build();
    }

    public Autor updateAutor (AutorDTO autorDTO, Autor entity){
        return Autor.builder()
                .nome(autorDTO.getNome()!=null ? autorDTO.getNome() : entity.getNome())
                .id(entity.getId())
                .senha(autorDTO.getSenha()!=null ? autorDTO.getSenha() : entity.getSenha())
                .email(autorDTO.getEmail()!=null ? autorDTO.getEmail() : entity.getEmail())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .build();
    }

    public Endereco updateEndereco (EnderecoDTO dto, Endereco entity){
        return Endereco.builder()
                .id(entity.getId())
                .rua(dto.getRua()!= null ? dto.getRua() : entity.getRua())
                .numero(dto.getNumero()!= null ? dto.getNumero() : entity.getNumero())
                .cidade(dto.getCidade()!= null ? dto.getCidade() : entity.getCidade())
                .cep(dto.getCep()!= null ? dto.getCep() : entity.getCep())
                .complemento(dto.getComplemento()!= null ? dto.getComplemento() : entity.getComplemento())
                .estado(dto.getEstado()!= null ? dto.getEstado() : entity.getEstado())
                .build();
    }

    public Telefone updateTelefone (TelefoneDTO dto, Telefone entity){
        return Telefone.builder()
                .id(entity.getId())
                .numero(dto.getNumero()!= null ? dto.getNumero() : entity.getNumero())
                .ddd(dto.getDdd()!=null ? dto.getDdd() : entity.getDdd())
                .build();
    }

    public Endereco paraEnderecoEntity(EnderecoDTO dto, Long idAutor){
        return Endereco.builder()
                .rua(dto.getRua())
                .cidade(dto.getCidade())
                .cep(dto.getCep())
                .complemento(dto.getComplemento())
                .estado(dto.getEstado())
                .numero(dto.getNumero())
                .autor_id(idAutor)
                .build();
    }

    public Telefone paraTelefoneEntity(TelefoneDTO dto, Long idAutor){
        return Telefone.builder()
                .ddd(dto.getDdd())
                .numero(dto.getNumero())
                .autor_id(idAutor)
                .build();
    }
}
