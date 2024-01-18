package com.swifttech.service.impl;

import com.swifttech.entity.Blogs;
import com.swifttech.entity.User;
import com.swifttech.entity.dto.response.BlogsResponseDTO;
import com.swifttech.entity.mapper.BlogMapper;
import com.swifttech.exceptions.BaseException;
import com.swifttech.repository.BlogsRepository;
import com.swifttech.repository.UserRepository;
import com.swifttech.service.BlogService;
import com.swifttech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogsRepository blogsRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public List<BlogsResponseDTO> getAllBlogs() {
        return BlogMapper.INSTANSE.toDTOs(this.blogsRepository.findAll());
    }

    @Override
    public BlogsResponseDTO addBlogs(Blogs blogs) {

        User user = userService.getLoggedUserDetails();

        blogs.setBlogCode(UUID.randomUUID());
        blogs.setCreatedBy(user);
        blogs.setCreatedDate(LocalDateTime.now());

        return BlogMapper.INSTANSE.toDto(blogs);

    }

    @Override
    public BlogsResponseDTO getBlogByCode(UUID code) {
        Optional<Blogs> blogs = this.blogsRepository.findByBlogCode(code);
        if (blogs.isEmpty()) {
            throw new BaseException(
                    "Blog not found",
                    HttpStatus.NOT_FOUND.value(),
                    "INVALID BLOG CODE"
                    );
        }
        return BlogMapper.INSTANSE.toDto(blogs.get());
    }

    @Override
    public List<BlogsResponseDTO> getBlogByCreator(UUID userCode) {
        Optional<User> user = this.userRepository.findByUserCode(userCode);
        if(user.isEmpty()){
            throw new BaseException(
                    "User Not found",
                    HttpStatus.NOT_FOUND.value(),
                    "INVALID USER CODE"
            );
        }
        List<Blogs> blogsList = this.blogsRepository.findAllByCreatedBy_UserCode(userCode);
        return BlogMapper.INSTANSE.toDTOs(blogsList);
    }
}
