package com.swifttech.service;


import com.swifttech.entity.Course;
import com.swifttech.entity.FileStorage;
import com.swifttech.entity.dto.request.CourseRequestDTO;
import com.swifttech.entity.dto.response.CourseResponseDTO;
import com.swifttech.entity.mapper.CourseMapper;
import com.swifttech.exceptions.BaseException;
import com.swifttech.repository.CourseRepository;
import com.swifttech.repository.PaymentRepository;
import com.swifttech.repository.UserRepository;
import com.swifttech.service.impl.CourseServiceImpl;
import com.swifttech.utils.FileHandling;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
 class CourseServiceTest {

    @Mock
    CourseRepository courseRepository;

    @Mock
    FileHandling fileHandling;


    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void saveCourse() {


        FileStorage fileStorage = new FileStorage(1L, "asf", "as", "sa", LocalDateTime.now(), "as");
        Course mockCourse = createCourse(1L, "Course 1", "Description 1", "course-1", 100,fileStorage);

        when(fileHandling.handleFile(Mockito.any())).thenReturn(fileStorage);
        when(courseRepository.save(Mockito.any(Course.class))).thenReturn(mockCourse);


        CourseRequestDTO courseRequestDTO = CourseMapper.INSTANCE.toDTO(mockCourse);
        CourseResponseDTO courseResponseDTO = courseService.addNewCourse(courseRequestDTO);


        assertNotNull(courseResponseDTO);
        assertEquals(mockCourse.getId(), courseResponseDTO.getId());
        assertEquals(mockCourse.getCourseName(), courseResponseDTO.getCourseName());
        assertEquals(mockCourse.getStatus(), courseResponseDTO.getStatus());
        assertEquals(mockCourse.getPrice(), courseResponseDTO.getPrice());
        assertEquals(mockCourse.getDescription(), courseResponseDTO.getDescription());
        assertEquals(mockCourse.getSlug(), courseResponseDTO.getSlug());
        assertEquals(mockCourse.getEnrolledCount(), courseResponseDTO.getEnrolledCount());
        assertEquals(mockCourse.getFile(), courseResponseDTO.getFile());

    }


    @Test
    public void testGetAllCoursesReturnsListOfAllCoursesWithDetails() {
        List<Course> courses = createCoursesList();
        when(courseRepository.findAll()).thenReturn(courses);
        List<CourseResponseDTO> result = courseService.getAllCourses();

        assertCoursesListEquals(courses, result);
    }

    private List<Course> createCoursesList() {
        FileStorage fileStorage = new FileStorage(1L, "asf", "as", "sa", LocalDateTime.now(), "as");
        List<Course> courses = new ArrayList<>();
        courses.add(createCourse(1L, "Course 1", "Description 1", "course-1", 100,fileStorage));
        courses.add(createCourse(2L, "Course 2", "Description 2", "course-2", 200,fileStorage));
        return courses;
    }

    private Course createCourse(Long id, String courseName, String description, String slug, int price,FileStorage fileStorage) {
        return Course.builder()
                .id(id)
                .courseName(courseName)
                .description(description)
                .slug(slug)
                .enrolledCount(0)
                .price(price)
                .courseCode(UUID.randomUUID())
                .status("ACTIVE")
                .file(null)
                .users(Collections.emptyList())
                .courseDetails(Collections.emptyList())
                .build();
    }



    private void assertCoursesListEquals(List<Course> expectedCourses, List<CourseResponseDTO> actualCourses) {
        assertNotNull(actualCourses);
        assertEquals(expectedCourses.size(), actualCourses.size());

        for (int i = 0; i < expectedCourses.size(); i++) {
            Course expectedCourse = expectedCourses.get(i);
            CourseResponseDTO actualCourseDTO = actualCourses.get(i);

            assertEquals(expectedCourse.getId(), actualCourseDTO.getId());
            assertEquals(expectedCourse.getCourseName(), actualCourseDTO.getCourseName());
            assertEquals(expectedCourse.getDescription(), actualCourseDTO.getDescription());
            assertEquals(expectedCourse.getSlug(), actualCourseDTO.getSlug());
            assertEquals(expectedCourse.getEnrolledCount(), actualCourseDTO.getEnrolledCount());
            assertEquals(expectedCourse.getPrice(), actualCourseDTO.getPrice());
            assertEquals(expectedCourse.getCourseCode(), actualCourseDTO.getCourseCode());
            assertEquals(expectedCourse.getStatus(), actualCourseDTO.getStatus());
        }
    }


    @Test
    public void test_addNewCourse_throwsExceptionIfCourseRequestDTOIsNull() {
        CourseRequestDTO courseRequestDTO = null;
        assertThrows(BaseException.class, () -> courseService.addNewCourse(courseRequestDTO));
    }

}
