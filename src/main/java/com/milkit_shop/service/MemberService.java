package com.milkit_shop.service;

import com.milkit_shop.constant.LoginType;
import com.milkit_shop.constant.Role;
import com.milkit_shop.dto.KakaoTokenResponseDto;
import com.milkit_shop.dto.KakaoUserInfoDto;
import com.milkit_shop.entity.Member;
import com.milkit_shop.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService{
  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final WebClient webClient = WebClient.create();;

  @Value("${kakao.client_id}")
  private String kakaoClientId;

  @Value("${kakao.redirect_uri}")
  private String kakaoRedirectUri;

  @Value("${kakao.admin_key}")
  private String kakaoAdminKey;

  public Member saveLocalMember(Member member){
    Duplicate(member);
    String password=member.getPassword();
    String encPassword=bCryptPasswordEncoder.encode(password);
    member.setPassword(encPassword);
    member.setRole(Role.USER);
    member.setLoginType(LoginType.LOCAL);
    return memberRepository.save(member);
  }

  public Member findMemberByUserCode(String userCode){
    return memberRepository.findByUserCode(userCode);
  }

  private void Duplicate(Member member){
    Member existingMember = memberRepository.findByUserCode(member.getUserCode());
    if(existingMember != null){
      throw new IllegalStateException("이미 가입된 회원입니다.");
    }
  }

  public Member saveAdminMember(Member member){
    String password = member.getPassword();
    String encPassword=bCryptPasswordEncoder.encode(password);
    member.setPassword(encPassword);
    member.setRole(Role.ROLE_ADMIN);
    member.setLoginType(LoginType.LOCAL);
    Member exist = memberRepository.findByUserCode("admin");
    if(exist == null) {
      memberRepository.save(member);
    } else {
      System.out.println("멤버가 이미 존재합니다.");
    }
    return member;
  }

  public void deleteMember(Member member){
    if (member.getLoginType().equals(LoginType.KAKAO)) {
      // 카카오 어플리케이션 연결 끊기
      String url = "https://kapi.kakao.com/v1/user/unlink";

      String deletedUsername = webClient.post()
              .uri(url)
              .header("Authorization", "KakaoAK " + kakaoAdminKey)
              .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
              .body(BodyInserters.fromFormData("target_id_type", "user_id")
                      .with("target_id", member.getUserCode())
              )
              .retrieve()
              .bodyToMono(String.class)
              .block();
    }

    memberRepository.delete(member);
  }

  public Member findMemberById(Long id){
    return memberRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
  }

  public KakaoTokenResponseDto getTokenFromKakao(String code) {
    String uri = "https://kauth.kakao.com/oauth/token";

    return webClient.post()
            .uri(uri)
            .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
            .body(BodyInserters.fromFormData("grant_type", "authorization_code")
                    .with("client_id", kakaoClientId)
                    .with("redirect_uri", kakaoRedirectUri)
                    .with("code", code)
            )
            .retrieve()
            .bodyToMono(KakaoTokenResponseDto.class)
            .block();
  }

  public KakaoUserInfoDto getKakaoUserInfo(String accessToken) {
    String url = "https://kapi.kakao.com/v2/user/me";

    return webClient.get()
            .uri(url)
            .header("Authorization", "Bearer " + accessToken)
            .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
            .retrieve()
            .bodyToMono(KakaoUserInfoDto.class)
            .block();
  }

  public Member saveKakaoOAuthMember(KakaoUserInfoDto kakaoUserInfoDto) {
    String kakaoUserCode = kakaoUserInfoDto.getId().toString();
    Member existingMember = memberRepository.findByUserCode(kakaoUserCode);

    if (existingMember == null) {
      Member member = new Member();
      member.setUserCode(kakaoUserCode);
      member.setName(kakaoUserInfoDto.getKakaoAccount().getProfile().getNickname());
      String randomPassword = UUID.randomUUID().toString();
      String encPassword = bCryptPasswordEncoder.encode(randomPassword);
      member.setPassword(encPassword);
      member.setRole(Role.USER);
      member.setLoginType(LoginType.KAKAO);
      return memberRepository.save(member);
    }

    return existingMember;
  }

  // 카카오 OAuth 로그인시 쓰이는 메서드
  public void login(Member member, HttpSession session) {
    UserDetails userDetails = new org.springframework.security.core.userdetails.User(
            member.getUserCode(),
            member.getPassword(),
            Collections.singleton(new SimpleGrantedAuthority(member.getRole().toString()))
    );

    UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    SecurityContextHolder.getContext().setAuthentication(authToken);

    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
            SecurityContextHolder.getContext());
  }

  public List<Member> findAllMembers() {
    return memberRepository.findAll();
  }
}