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

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MemberService memberService;

    public CartDto getCartList(String email) {
        Cart cart = cartRepository.findByEmail(email);
        if (cart == null) {
            Member member = memberService.findMemberByEmail(email);
            cart = cartRepository.save(Cart.createCart(member));
        }

        CartDto cartDto = new CartDto(cart);

        List<CartItem> cartItemList = cart.getCartItems();
        for (CartItem cartItem: cartItemList) {
            CartItemDto cartItemDto = new CartItemDto(cartItem);
            cartDto.addCartItemDto(cartItemDto);
        }

        return cartDto;
    }

    @Transactional
    public Cart addCart(CartItemDto cartItemDto, String email) {
        Cart cart = cartRepository.findByEmail(email);
        if (cart == null) {
            Member member = memberService.findMemberByEmail(email);
            cart = cartRepository.save(Cart.createCart(member));
        }

        Item item = itemRepository.findById(cartItemDto.getItemId()).orElseThrow(EntityNotFoundException::new);

        CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
        cart.addCartItem(cartItem);

        return cartRepository.save(cart);
    }
}
