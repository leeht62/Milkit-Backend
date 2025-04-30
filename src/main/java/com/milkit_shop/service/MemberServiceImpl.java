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
    this.memberRepository = memberRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String userCode) throws UsernameNotFoundException {
    System.out.println("넘어온 userCode: " + userCode);
    System.out.println("loadUserByUsername 실행");

    Member member = memberRepository.findByUserCode(userCode);
    if (member == null) {
      throw new UsernameNotFoundException("User not found with userCode: " + userCode);
    }

    return new org.springframework.security.core.userdetails.User(
        member.getUserCode(),
        member.getPassword(),
        Collections.singleton(new SimpleGrantedAuthority(member.getRole().toString())));
  }
}
