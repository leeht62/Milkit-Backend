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


  public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{
    Item item = itemFormDto.createItem();
    itemRepository.save(item);
    for(int i=0;i<itemImgFileList.size();i++){
      ItemImg itemImg = new ItemImg();
      itemImg.setItem(item);
      if(i == 0)
        itemImg.setRepimgYn("Y");
      else
        itemImg.setRepimgYn("N");
      itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
      item.setImage(itemImg.getImgUrl());
    }
    return item.getId();
  }

  public Long updateItem(Long id,ItemFormDto itemFormDto,List<MultipartFile> itemImgFileList) throws
      Exception{
    Item item=itemRepository.findById(id)
        .orElseThrow(EntityNotFoundException::new);
    item.updateItem(itemFormDto);

    List<Long> itemImgIds=itemFormDto.getItemImgIds();

    for(int i=0;i<itemImgFileList.size();i++){
      itemImgService.updateItemImg(itemImgIds.get(i),itemImgFileList.get(i));
    }

    return item.getId();
  }
  public void deleteItem(Long id){
    Item item=itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    itemRepository.delete(item);
  }

}
