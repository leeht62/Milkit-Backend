package com.milkit_shop.service;

import com.milkit_shop.entity.Item;
import com.milkit_shop.entity.ItemImg;
import com.milkit_shop.repository.ItemImgRepository;
import com.milkit_shop.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {
  @Value("${itemImgLocation}")
  private String itemImgLocation;
  private final ItemImgRepository itemImgRepository;
  private final FileService fileService;
  private final ItemRepository itemRepository;
  private final S3Service s3Service;

  public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile)throws Exception{
    String oriImgName=itemImgFile.getOriginalFilename();
    String imgName="";
    String imgUrl="";

    if(oriImgName != null && !oriImgName.isBlank()){
//      imgName=fileService.uploadFile(itemImgLocation,oriImgName,
//          itemImgFile.getBytes());
//      imgUrl="/images/item/"+imgName;

      s3Service.uploadImg(itemImgFile, itemImg);

    }
//    itemImg.updateItemImg(oriImgName,imgName,imgUrl);

    itemImgRepository.save(itemImg);
  }

  public void updateItemImg(Long itemImgId, MultipartFile itemImgFile)throws Exception{
    if(!itemImgFile.isEmpty()){
      ItemImg savedItemImg=itemImgRepository.findById(itemImgId)
          .orElseThrow(EntityNotFoundException::new);
      Item item=itemRepository.findById(savedItemImg.getItem().getId())
          .orElseThrow(EntityNotFoundException::new);
      if(savedItemImg.getImgName() != null && !savedItemImg.getImgName().isBlank()){
//        fileService.deleteFile(itemImgLocation+"/"+savedItemImg.getImgName());
        s3Service.deleteImg(savedItemImg.getImgName());
      }
      String oriImgName=itemImgFile.getOriginalFilename();
//      String imgName= fileService.uploadFile(itemImgLocation,oriImgName,itemImgFile.getBytes());
//      String imgUrl="/images/item/"+imgName;
      s3Service.uploadImg(itemImgFile, savedItemImg);
//      savedItemImg.updateItemImg(oriImgName,imgName,imgUrl);
      item.setImage(savedItemImg.getImgUrl());
    }
  }

  public void deleteItemImg(Long itemImgId) {
    ItemImg savedItemImg=itemImgRepository.findById(itemImgId)
            .orElseThrow(EntityNotFoundException::new);
    s3Service.deleteImg(savedItemImg.getImgName());
    itemImgRepository.delete(savedItemImg);
  }
}
