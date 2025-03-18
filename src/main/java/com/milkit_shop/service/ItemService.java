package com.milkit_shop.service;

import com.milkit_shop.dto.ItemDto;
import com.milkit_shop.entity.Item;
import com.milkit_shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  public List<Item> finditemList() {
    return itemRepository.findAll();
  }

  @Transactional
  public void saveItem(ItemDto itemDTO) {
    Item item = changetoItem(itemDTO);
    itemRepository.save(item);
  }

  //전체 상품목록 조회
  @Transactional
  public List<ItemDto> itemList() {
    List<Item> items = itemRepository.findAll();
    return changeToDTOList(items);
  }


  private List<ItemDto> changeToDTOList(List<Item> items) {
    return items.stream()
        .map(item -> {
          ItemDto dto = new ItemDto();
          dto.setId(item.getId());
          dto.setName(item.getName());
          dto.setPrice(item.getPrice());
          dto.setStock(item.getStockNumber());
          dto.setImage(item.getImage());
          dto.setContent(item.getContent());
          return dto;
        })
        .collect(Collectors.toList());
  }

  public Item changetoItem(ItemDto itemDto){
    Item item=new Item();
    item.setName(itemDto.getName());
    item.setId(itemDto.getId());
    item.setImage(itemDto.getImage());
    item.setContent(itemDto.getContent());
    item.setPrice(itemDto.getPrice());
    item.setStockNumber(itemDto.getStock());
    return item;
  }


}
