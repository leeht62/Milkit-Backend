package com.milkit_shop.controller;

import com.milkit_shop.entity.Member;
import com.milkit_shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;
  @GetMapping("/loginOk")
  public ResponseEntity<Map<String, String>> loginOk() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    String authorities = authentication.getAuthorities().toString();

    System.out.println("로그인한 유저 이메일:" + email);
    System.out.println("유저 권한:" + authentication.getAuthorities());

    Map<String, String> MemberInfo = new HashMap<>();
    MemberInfo.put("email", email);
    MemberInfo.put("authorities", authorities);

    return ResponseEntity.ok(MemberInfo);  // 클라이언트에게 사용자 정보를 반환
  }

  @PostMapping("/join")
  public ResponseEntity<Void> join(@RequestBody Member member) {
    memberService.saveMember(member);
    System.out.println("회원가입 요청 받은 MemberDto: " + member);
    System.out.println("회원가입 완료");
    return ResponseEntity.ok().build();
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

}