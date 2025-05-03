package com.milkit_shop.controller;

import com.milkit_shop.dto.ItemDto;
import com.milkit_shop.dto.ItemFormDto;
import com.milkit_shop.service.ItemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
  private final ItemService itemService;


  @GetMapping("/list")
  public ResponseEntity<List<ItemDto>> getAllItems(@RequestParam(value = "search", required = false) String search) {
    List<ItemDto> itemList = itemService.itemList();
    if (search == null){
      return ResponseEntity.ok(itemList);
    }else{
      return ResponseEntity.ok(itemService.itemListSearch(search));
    }
  }

  // 단일 상품 조회
  @GetMapping(value="/item/{itemId}")
  public ResponseEntity<?> getItem(@PathVariable("itemId") Long itemId){
    try {
      ItemFormDto itemFormDto = itemService.getItem(itemId);
      return ResponseEntity.ok(itemFormDto);
    } catch(EntityNotFoundException e){
      return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
    }
  }

  // 상품 등록
  @PostMapping(value="/admin/item/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> createItem(@RequestPart("itemFormDto") ItemFormDto itemFormDto, @RequestPart("itemImgFile") List<MultipartFile> itemImgFileList) throws Exception {

    try {
      Long createdItemId = itemService.saveItem(itemFormDto,itemImgFileList);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdItemId);
    } catch(Exception e){
      return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
    }
  }

  // 상품 수정
  @PutMapping(value="/admin/item/{itemId}")
  public ResponseEntity<?> updateItem(@RequestPart("itemFormDto") ItemFormDto itemFormDto, @RequestPart("itemImgFile") List<MultipartFile> itemImgFileList) {
    try {
      Long updatedItemId = itemService.updateItem(itemFormDto,itemImgFileList);
      return ResponseEntity.ok(updatedItemId);
    } catch(Exception e){
      return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
    }
  }

  // 상품 삭제
  @DeleteMapping(value="/admin/item/{itemId}")
  public ResponseEntity<?> deleteItem(@PathVariable("itemId") Long itemId) {
    try {
      itemService.deleteItem(itemId);
      return ResponseEntity.ok(null);
    } catch(Exception e){
      return ResponseEntity.internalServerError().body(Map.of("error", e.getMessage()));
    }
  }
}
