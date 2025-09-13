package br.edu.ada.DTO.Mapper;

import br.edu.ada.DTO.ProdutoRequestDTO;
import br.edu.ada.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi")
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    Produto toEntity(ProdutoRequestDTO produtoRequestDTO);

    void updateEntityFromDTO(ProdutoRequestDTO produtoRequestDTO, @MappingTarget Produto produto);
}
