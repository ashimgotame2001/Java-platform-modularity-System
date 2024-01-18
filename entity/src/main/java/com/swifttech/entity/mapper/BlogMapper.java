package com.swifttech.entity.mapper;

import com.swifttech.entity.Blogs;
import com.swifttech.entity.dto.response.BlogsResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BlogMapper {

    BlogMapper INSTANSE = Mappers.getMapper(BlogMapper.class);

    Blogs toEntity(BlogsResponseDTO responseDTO);

    List<Blogs> toEntities(List<BlogsResponseDTO> blogsResponseDTOS);

    BlogsResponseDTO toDto(Blogs blogs);

    List<BlogsResponseDTO> toDTOs(List<Blogs> blogs);
}
