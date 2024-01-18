package com.swifttech.repository;


import com.swifttech.entity.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlogsRepository extends JpaRepository<Blogs,Long> {

    Optional<Blogs> findByBlogCode(UUID code);

    List<Blogs> findAllByCreatedBy_UserCode(UUID userCode);
}
