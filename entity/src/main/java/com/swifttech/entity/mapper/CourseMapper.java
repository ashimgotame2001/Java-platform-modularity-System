package com.swifttech.entity.mapper;

import com.swifttech.entity.Course;
import com.swifttech.entity.dto.request.CourseRequestDTO;
import com.swifttech.entity.dto.response.CourseResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseMapper {
    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course toEntity(CourseRequestDTO dto);


    CourseResponseDTO toResponse(Course course);

    List<CourseResponseDTO> toResponses(List<Course> courses);

    CourseRequestDTO toDTO(Course course);


}
