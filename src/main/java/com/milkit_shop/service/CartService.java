package com.milkit_shop.service;

import com.milkit_shop.dto.CartDto;
import com.milkit_shop.dto.CartItemDto;
import com.milkit_shop.entity.Cart;
import com.milkit_shop.entity.CartItem;
import com.milkit_shop.entity.Item;
import com.milkit_shop.entity.Member;
import com.milkit_shop.repository.CartRepository;
import com.milkit_shop.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MemberService memberService;

    public CartDto getCart (String userCode) {
        Cart cart = cartRepository.findByUserCode(userCode);
        if (cart == null) {
            Member member = memberService.findMemberByUserCode(userCode);
            cart = cartRepository.save(Cart.createCart(member));
        }

        return new CartDto(cart);
    }

    @Transactional
    public CartDto addItem(CartItemDto cartItemDto, String userCode) {
        Cart cart = cartRepository.findByUserCode(userCode);
        if (cart == null) {
            Member member = memberService.findMemberByUserCode(userCode);
            cart = cartRepository.save(Cart.createCart(member));
        }

        Item item = itemRepository.findById(cartItemDto.getItemId()).orElseThrow(EntityNotFoundException::new);

        CartItem cartItem = cart.findByItemId(item.getId());
        if (cartItem == null) {
            cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cart.addCartItem(cartItem);
        } else {
            cart.increaseCartItemCount(cartItem, cartItemDto.getCount());
        }

        Cart updated = cartRepository.save(cart);
        return new CartDto(updated);
    }

    @Transactional
    public CartDto decreaseItemCount(Long itemId, String userCode) {
        Cart cart = cartRepository.findByUserCode(userCode);

        CartItem cartItem = cart.findByItemId(itemId);
        if (cartItem == null) {
            return null;
        }

        if (cartItem.getCount() <= 1) {
            return deleteItem(itemId, userCode);
        }

        cart.decreaseCartItemCount(cartItem);
        Cart updated = cartRepository.save(cart);
        return new CartDto(updated);
    }

    public CartDto deleteItem(Long itemId, String userCode) {
        Cart cart = cartRepository.findByUserCode(userCode);

        CartItem cartItem = cart.findByItemId(itemId);
        if (cartItem == null) {
            return null;
        }

        cart.deleteCartItem(cartItem);
        Cart updated = cartRepository.save(cart);
        return new CartDto(updated);
    }
}
