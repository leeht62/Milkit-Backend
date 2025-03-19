package com.milkit_shop.service;

import com.milkit_shop.dto.ItemDto;
import com.milkit_shop.entity.Item;
import com.milkit_shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
  @Autowired ItemRepository itemRepository;

  public void saveItem(Item item) {
    itemRepository.save(item);
  }

  //전체 상품목록 조회
  @Transactional
  public List<ItemDto> itemList() {
    List<Item> items = itemRepository.findAll();
    List<ItemDto> ItemDtoList = new ArrayList<>();
    // main에 필요한 값들만 Dto로 만들어서 보내준다.
    for(int i=0; i<items.size(); i++){
      ItemDto mainDto = new ItemDto(items.get(i));
      ItemDtoList.add(mainDto);
    }
    return ItemDtoList;
  }




}
