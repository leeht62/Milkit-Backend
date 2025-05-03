package com.milkit_shop.service;

import com.milkit_shop.entity.Item;
import com.milkit_shop.entity.ItemImg;
import com.milkit_shop.repository.ItemImgRepository;
import com.milkit_shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {
  @Value("${itemImgLocation}")
  private String itemImgLocation;
  private final ItemImgRepository itemImgRepository;
  private final FileService fileService;
  private final ItemRepository itemRepository;

  public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile)throws Exception{
    String oriImgName=itemImgFile.getOriginalFilename();
    String imgName="";
    String imgUrl="";

    if(oriImgName != null && !oriImgName.isBlank()){
      imgName=fileService.uploadFile(itemImgLocation,oriImgName,
          itemImgFile.getBytes());
      imgUrl="/images/item/"+imgName;
    }
    itemImg.updateItemImg(oriImgName,imgName,imgUrl);
    itemImgRepository.save(itemImg);
  }
  public void updateItemImg(Long itemImgId, MultipartFile itemImgFile)throws Exception{
    if(!itemImgFile.isEmpty()){
      ItemImg savedItemImg=itemImgRepository.findById(itemImgId)
          .orElseThrow(EntityNotFoundException::new);
      Item item=itemRepository.findById(savedItemImg.getItem().getId())
          .orElseThrow(EntityNotFoundException::new);
      if(savedItemImg.getImgName() != null && !savedItemImg.getImgName().isBlank()){
        fileService.deleteFile(itemImgLocation+"/"+savedItemImg.getImgName());
      }
      String oriImgName=itemImgFile.getOriginalFilename();
      String imgName= fileService.uploadFile(itemImgLocation,oriImgName,itemImgFile.getBytes());
      String imgUrl="/images/item/"+imgName;
      savedItemImg.updateItemImg(oriImgName,imgName,imgUrl);
      item.setImage(savedItemImg.getImgUrl());
    }


  }


}
