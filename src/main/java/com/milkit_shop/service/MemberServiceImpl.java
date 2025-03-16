package com.milkit_shop.service;

import com.milkit_shop.entity.Member;
import com.milkit_shop.repository.MemberRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MemberServiceImpl implements UserDetailsService {
  private final MemberRepository memberRepository;

  public MemberServiceImpl(MemberRepository memberRepository){
    this.memberRepository=memberRepository;
  }
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    System.out.println("넘어온 이메일: " + email);
    System.out.println("loadUserByUsername 실행");

    // 사용자 조회, 없으면 예외 발생
    Member member = memberRepository.findByEmail(email);
    if (member == null) {
      throw new UsernameNotFoundException("User not found with email: " + email);
    }

    // 사용자가 있다면 UserDetails 객체 생성
    return new org.springframework.security.core.userdetails.User(
        member.getEmail(),
        member.getPassword(),
        Collections.singleton(new SimpleGrantedAuthority(member.getRole().toString())));
  }
}
