package com.milkit_shop.service;

import com.milkit_shop.constant.Login;
import com.milkit_shop.constant.Role;
import com.milkit_shop.entity.Cart;
import com.milkit_shop.entity.Member;
import com.milkit_shop.repository.CartRepository;
import com.milkit_shop.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService{
  private final MemberRepository memberRepository;
  private final CartRepository cartRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public Member saveMember(Member member){
    Duplicate(member);
    String password=member.getPassword();
    String encPassword=bCryptPasswordEncoder.encode(password);
    member.setPassword(encPassword);
    member.setRole(Role.USER);
    member.setLogin(Login.Register);
    return memberRepository.save(member);
  }

  public Member findMemberByEmail(String email){
    return memberRepository.findByEmail(email);
  }
  private void Duplicate(Member member){
    Member memberemail=memberRepository.findByEmail(member.getEmail());
    if(memberemail!=null){
      throw new IllegalStateException("이미 가입된 회원입니다.");
    }
  }

  public Member saveAdminMember(Member member){
    String password=member.getPassword();
    String encPassword=bCryptPasswordEncoder.encode(password);
    member.setPassword(encPassword);
    member.setRole(Role.ROLE_ADMIN);
    Member exist = memberRepository.findByEmail("admin@naver.com");
    if(exist==null) {
      memberRepository.save(member);
    }else{
      System.out.println("멤버가 이미 존재합니다.");
    }
    return member;
  }

  public void deleteMember(Member member){
    memberRepository.delete(member);
  }

  public Member findMemberById(Long id){
    return memberRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
  }






}