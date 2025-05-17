package com.milkit_shop.service;

import com.milkit_shop.dto.ItemDto;
import com.milkit_shop.dto.ItemFormDto;
import com.milkit_shop.entity.Item;
import com.milkit_shop.entity.ItemImg;
import com.milkit_shop.repository.ItemImgRepository;
import com.milkit_shop.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {
  @Autowired ItemRepository itemRepository;
  @Autowired ItemImgRepository itemImgRepository;
  @Autowired ItemImgService itemImgService;

  public void saveItem(Item item) {
    List<Item> existingItem = itemRepository.findByName(item.getName());
    if (existingItem.isEmpty()) {
      itemRepository.save(item);
    } else {
      Item items = existingItem.get(0);
      item.setId(items.getId());
      itemRepository.save(item);
    }
  }

  @Transactional
  public List<ItemDto> itemList() {
    List<Item> items = itemRepository.findAll();
    List<ItemDto> ItemDtoList = new ArrayList<>();
    for (int i = 0; i < items.size(); i++){
      ItemDto mainDto = new ItemDto(items.get(i));
      ItemDtoList.add(mainDto);
    }
    return ItemDtoList;
  }

  public List<ItemDto> itemListSearch(String search) {
    List<Item> items = itemRepository.findItemsBySearch(search);
    List<ItemDto> ItemDtoList = new ArrayList<>();
    for (int i = 0; i < items.size(); i++){
      ItemDto mainDto = new ItemDto(items.get(i));
      ItemDtoList.add(mainDto);
    }
    return ItemDtoList;
  }


  public Long saveItem(ItemFormDto itemFormDto, MultipartFile itemImgFile) throws Exception{
    Item item = itemFormDto.createItem();
    itemRepository.save(item);

    ItemImg itemImg = new ItemImg();
    itemImg.setItem(item);
    itemImgService.saveItemImg(itemImg, itemImgFile);
    item.setImage(itemImg.getImgUrl());

    return item.getId();
  }

  public Long updateItem(Long id,ItemFormDto itemFormDto, MultipartFile itemImgFile) throws
      Exception{
    Item item = itemRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
    item.updateItem(itemFormDto);

    if (item.getItemImgs() == null || item.getItemImgs().isEmpty()) {
      ItemImg newImg = new ItemImg();
      newImg.setItem(item);
      itemImgService.saveItemImg(newImg, itemImgFile);
      item.setImage(newImg.getImgUrl());
    } else {
      Long itemImgId = item.getItemImgs().get(0).getId();
      itemImgService.updateItemImg(itemImgId, itemImgFile);
      ItemImg updatedImg = itemImgRepository.findById(itemImgId)
          .orElseThrow(EntityNotFoundException::new);
      item.setImage(updatedImg.getImgUrl());
    }

    return item.getId();
  }

  public void deleteItem(Long id){
    Item item = itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    itemImgService.deleteItemImg(item.getItemImgs().getFirst().getId());
    itemRepository.delete(item);
  }

}