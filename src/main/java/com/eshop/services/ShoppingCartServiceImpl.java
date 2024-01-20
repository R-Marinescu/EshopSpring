package com.eshop.services;

import com.eshop.DTO.ProductDTO;
import com.eshop.DTO.ShoppingCartDTO;
import com.eshop.DTO.UserDTO;
import com.eshop.models.ShoppingCart;
import com.eshop.repositories.ShoppingCartRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepo shoppingCartRepo;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepo shoppingCartRepo, UserService userService, ProductService productService) {
        this.userService = userService;
        this.shoppingCartRepo = shoppingCartRepo;
        this.productService = productService;
    }

    public ShoppingCartDTO convertShoppingCartToDTO(ShoppingCart shoppingCart) {
        UserDTO userDTO = userService.convertUserToDTO(shoppingCart.getUser());
        ProductDTO productDTO = productService.convertProductToDTO(shoppingCart.getProduct());

        return new ShoppingCartDTO(
                shoppingCart.getCartId(),
                userDTO,
                productDTO,
                shoppingCart.getQuantity()
        );
    }

    @Override
    public Optional<ShoppingCartDTO> getShoppingCartById(Integer cartId) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepo.findById(cartId);

        return optionalShoppingCart.map(this::convertShoppingCartToDTO);
    }

    @Override
    public List<ShoppingCartDTO> getAllShoppingCarts() {
        return shoppingCartRepo.findAll()
                .stream()
                .map(this::convertShoppingCartToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ShoppingCart createShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setUser(userService.convertDTOToUser(shoppingCartDTO.getUserDTO()));
        shoppingCart.setProduct(productService.convertDTOToProduct(shoppingCartDTO.getProductDTO()));
        shoppingCart.setQuantity(shoppingCartDTO.getQuantity());

        return shoppingCartRepo.save(shoppingCart);
    }

    @Override
    public ShoppingCart updateShoppingCart(Integer cartId, ShoppingCartDTO shoppingCartDTO) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepo.findById(cartId);

        if(optionalShoppingCart.isPresent()) {
            ShoppingCart existingShoppingCart = optionalShoppingCart.get();

            if (shoppingCartDTO.getProductDTO() != null) {
                existingShoppingCart.setProduct(productService.convertDTOToProduct(shoppingCartDTO.getProductDTO()));
            }

            if (shoppingCartDTO.getQuantity() != null) {
                existingShoppingCart.setQuantity(shoppingCartDTO.getQuantity());
            }
            shoppingCartRepo.save(existingShoppingCart);

            return existingShoppingCart;
        } else {
            throw new EntityNotFoundException("ShoppingCart with ID " + cartId + " not found");
        }
    }

    @Override
    public void deleteShoppingCart(Integer cartId) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepo.findById(cartId);

        if(optionalShoppingCart.isPresent()){
            shoppingCartRepo.deleteById(cartId);
        }
    }
}
