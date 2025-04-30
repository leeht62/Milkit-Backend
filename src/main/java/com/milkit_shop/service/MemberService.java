package com.milkit_shop.service;

import com.milkit_shop.constant.LoginType;
import com.milkit_shop.constant.Role;
import com.milkit_shop.entity.Member;
import com.milkit_shop.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService{
  private final MemberRepository memberRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
    memberRepository.delete(member);
  }

  public Member findMemberById(Long id){
    return memberRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
  }
}