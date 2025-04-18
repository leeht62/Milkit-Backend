package com.milkit_shop.controller;

import com.milkit_shop.entity.Member;
import com.milkit_shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class MemberController {
  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;
  @GetMapping("/loginOk")
  public ResponseEntity<Member> loginOk() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String email = authentication.getName();
    Member member = memberService.findMemberByEmail(email);
    if (member == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    System.out.println("로그인한 유저 이메일:" + email);
    return ResponseEntity.ok(member);
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
  @PostMapping("/{MemberId}/deleteMember")
  public ResponseEntity<Void> delete(@PathVariable Long MemberId) {
    Member member=memberService.findMemberById(MemberId);
    memberService.deleteMember(member);
    return ResponseEntity.ok().build();
  }

}