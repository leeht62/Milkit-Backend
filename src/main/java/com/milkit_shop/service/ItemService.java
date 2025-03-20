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
    List<Item> existingItem = itemRepository.findByName(item.getName());
    if (existingItem.isEmpty()) {
      itemRepository.save(item);
    }

  }

  @Transactional
  public List<ItemDto> itemList() {
    List<Item> items = itemRepository.findAll();
    List<ItemDto> ItemDtoList = new ArrayList<>();
    for(int i=0; i<items.size(); i++){
      ItemDto mainDto = new ItemDto(items.get(i));
      ItemDtoList.add(mainDto);
    }
    return ItemDtoList;
  }




}
