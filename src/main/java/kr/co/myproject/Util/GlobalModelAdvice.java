package kr.co.myproject.Util;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;
import kr.co.myproject.dto.User.SessionUser;
import kr.co.myproject.dto.User.UserDto;
import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAdvice {
//    @ModelAttribute("popularTop5Post")
//    public List<PopularPostListDto> popularTop5Post() {
//        List<PopularPostListDto> dto = postService.findPopularTop5Post();
//
//        return dto;
//    }
//
//    @ModelAttribute("sidebarCategoryList")
//    public List<SidebarCategoryDto> sidebarCategoryList() {
//        List<SidebarCategoryDto> dtos = boardService.findSidebarCategoryAll();
//
//       return dtos;
//    }
//
//    @ModelAttribute("sidebarNoticeList")
//    public List<SidebarNoticeDto> sidebarNoticeList() {
//    List<SidebarNoticeDto> dto = postService.findSidebarNoticeList();
//
//    return dto;
//}
}
