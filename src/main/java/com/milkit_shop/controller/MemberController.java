package com.milkit_shop.controller;

import com.milkit_shop.dto.KakaoUserInfoDto;
import com.milkit_shop.dto.KakaoTokenResponseDto;
import com.milkit_shop.entity.Member;
import com.milkit_shop.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;
  @GetMapping("/loginOk")
  public ResponseEntity<Member> loginOk() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userCode = authentication.getName();
    Member member = memberService.findMemberByUserCode(userCode);
    if (member == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    System.out.println("로그인한 유저 코드:" + userCode);
    return ResponseEntity.ok(member);
  }

  @PostMapping("/join")
  public ResponseEntity<?> join(@RequestBody Member member) {
    try {
      memberService.saveLocalMember(member);
      return ResponseEntity.ok().build();
    }catch (IllegalStateException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
  }
  @GetMapping(value="/login")
  public ResponseEntity<Void> loginMember(){
    return ResponseEntity.ok().build();
  }
  @GetMapping("/logoutOk")
  public ResponseEntity<Void> logoutOk() {
    System.out.println("로그아웃 성공");
    return ResponseEntity.ok().build();
  }
  @GetMapping(value="/login/error")
  public ResponseEntity<String> loginError(){
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 오류");
  }
  @PostMapping("/{MemberId}/deleteMember")
  public ResponseEntity<Void> delete(@PathVariable Long MemberId) {
    Member member=memberService.findMemberById(MemberId);
    memberService.deleteMember(member);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/oauth/login/callback")
  public ResponseEntity<Void> oauthLoginCallback(@RequestParam String code, HttpSession session, HttpServletResponse response) throws IOException {

    KakaoTokenResponseDto kakaoTokenResponseDto = memberService.getTokenFromKakao(code);
    KakaoUserInfoDto kakaoUserInfoDto = memberService.getKakaoUserInfo(kakaoTokenResponseDto.getAccessToken());

    Member member = memberService.saveKakaoOAuthMember(kakaoUserInfoDto);
    memberService.login(member, session);
    response.sendRedirect("https://bugifood.shop");
    return ResponseEntity.ok().build();
  }

  @GetMapping("/admin/members")
  public ResponseEntity<List<Member>> getAllMembers() {
    List<Member> members = memberService.findAllMembers();
    return ResponseEntity.ok(members);
  }

  @PostMapping("/admin/{id}/deleteMember")
  public ResponseEntity<Void> adminDelete(@PathVariable Long id){
    Member member = memberService.findMemberById(id);
    memberService.deleteMember(member);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/deleteMember")
  public ResponseEntity<Void> selfDelete(Principal principal){
    String userCode = principal.getName();
    Member member = memberService.findMemberByUserCode(userCode);
    memberService.deleteMember(member);
    return ResponseEntity.ok().build();
  }

}