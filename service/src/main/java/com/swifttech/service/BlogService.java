package com.swifttech.service;

import com.swifttech.entity.Blogs;
import com.swifttech.entity.dto.response.BlogsResponseDTO;

import java.util.List;
import java.util.UUID;

public interface BlogService {

    List<BlogsResponseDTO> getAllBlogs();

    BlogsResponseDTO addBlogs(Blogs blogs);

    BlogsResponseDTO getBlogByCode(UUID code);

    List<BlogsResponseDTO> getBlogByCreator(UUID userCode);
}
