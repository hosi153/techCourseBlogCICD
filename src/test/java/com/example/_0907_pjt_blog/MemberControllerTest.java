package com.example._0907_pjt_blog;


import com.example._0907_pjt_blog.controller.MemberController;
import com.example._0907_pjt_blog.controller.dto.MemberPostDto;
import com.example._0907_pjt_blog.controller.dto.MemberResponseDto;
import com.example._0907_pjt_blog.entity.Member;
import com.example._0907_pjt_blog.repository.MemberRepository;
import com.example._0907_pjt_blog.service.MemberService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class MemberControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @MockBean
    private MemberService memberService;


    @DisplayName("CONTROLLER : member 생성")
    @Test
    void newMemberTest() throws Exception {
        //given
        //MemberPostDto memberPostDto = (MemberPostDto) StubData.MockMember.getRequestBody(HttpMethod.POST);
        MemberPostDto memberPostDto = new MemberPostDto("id","pwd","name");
        Member member = MemberPostDto.memberPostDtoToMember(memberPostDto);
        //given(MemberPostDto.memberPostDtoToMember(memberPostDto)).willReturn(new Member());

        given(memberService.createMember(Mockito.any(Member.class))).willReturn(member);

        // (5) 응답값 처리하는 부분
        Member mockResultMember = new Member();
        mockResultMember.setMemberId(1L);
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(mockResultMember);

        //when
        String content = gson.toJson(memberPostDto);
        ResultActions actions = mockMvc.perform(
                post("/api/member/new")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        //then
        actions.andExpect(status().isCreated())
                .andDo(document("post-member",     // (7-1)
                        getRequestPreProcessor(),      // (7-2)
                        getResponsePreProcessor(),     // (7-3)
                        requestFields(             // (7-4)
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"), // (7-5)
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("phone").type(JsonFieldType.STRING).description("휴대폰 번호")
                                )
                        ),
                        responseHeaders(        // (7-6)
                                headerWithName(HttpHeaders.LOCATION).description("Location header. 등록된 리소스의 URI")
                        ));

    }
}
