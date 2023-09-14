package com.example._0907_pjt_blog.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDto {

    private String loginId;
    private String name;
}
