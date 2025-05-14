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
  private final ItemImgRepository itemImgRepository;
  private final S3Service s3Service;

  public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception{
    String oriImgName=itemImgFile.getOriginalFilename();

    if(oriImgName != null && !oriImgName.isBlank()){
      s3Service.uploadImg(itemImgFile, itemImg);
    }

    itemImgRepository.save(itemImg);
  }

  public void updateItemImg(Long itemImgId, MultipartFile itemImgFile)throws Exception{
    if(!itemImgFile.isEmpty()){
      ItemImg savedItemImg = itemImgRepository.findById(itemImgId)
          .orElseThrow(EntityNotFoundException::new);

      if(savedItemImg.getImgName() != null && !savedItemImg.getImgName().isBlank()){
        s3Service.deleteImg(savedItemImg.getImgName());
      }

      saveItemImg(savedItemImg, itemImgFile);
    }
  }

  public void deleteItemImg(Long itemImgId) {
    ItemImg savedItemImg = itemImgRepository.findById(itemImgId)
            .orElseThrow(EntityNotFoundException::new);
    s3Service.deleteImg(savedItemImg.getImgName());
    itemImgRepository.delete(savedItemImg);
  }
}
