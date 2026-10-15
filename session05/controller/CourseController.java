
package com.example.session05.controller;

import com.example.session05.dto.ApiResponse;
import com.example.session05.dto.CourseResponse;
import com.example.session05.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.session05.dto.PageResponse;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> getCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction) {

        PageResponse<CourseResponse> data =
                courseService.getPagedCourses(page, size, sortBy, direction);

        ApiResponse<PageResponse<CourseResponse>> response =
                new ApiResponse<>(200, "Success", data);

        return ResponseEntity.ok(response);
    }

}
