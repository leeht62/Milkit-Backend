package com.milkit_shop.service;

import com.milkit_shop.dto.OrderDto;
import com.milkit_shop.entity.Item;
import com.milkit_shop.entity.Member;
import com.milkit_shop.repository.ItemRepository;
import com.milkit_shop.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
  private final ItemRepository itemRepository;
  private final MemberRepository memberRepository;


}
