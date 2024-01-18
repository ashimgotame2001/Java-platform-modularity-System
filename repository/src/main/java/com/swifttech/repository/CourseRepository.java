package com.swifttech.repository;

import com.swifttech.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    Optional<Course> findByCourseCode(UUID code);


    @Query(
            value = "select * from course cs inner join public.course_users cu on cs.id =cu.course_id where users_id=:userId",nativeQuery = true
    )
    List<Course> getAllEnrolledCourseByUser(@Param("userId") Long userId);

}
