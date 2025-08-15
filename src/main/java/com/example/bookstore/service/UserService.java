package com.example.bookstore.service;

import com.example.bookstore.entity.User;
import com.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookstore.repository.WishlistRepository;
import com.example.bookstore.repository.UserAddressRepository;
import com.example.bookstore.repository.OrdersRepository;

import java.util.List;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    public Optional<User> login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password));
    }

    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean isUsernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public User register(User user) throws DataIntegrityViolationException {
        // Check if email already exists
        if (isEmailExists(user.getEmail())) {
            throw new DataIntegrityViolationException("Email đã được sử dụng");
        }

        // Check if username already exists
        if (isUsernameExists(user.getUsername())) {
            throw new DataIntegrityViolationException("Tên đăng nhập đã được sử dụng");
        }

        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(Integer id) {
        // If user has orders, do not allow deletion to avoid breaking order history
        long orderCount = ordersRepository.countByUserId(id);
        if (orderCount > 0) {
            throw new IllegalStateException("Người dùng có đơn hàng, không thể xóa.");
        }

        // Remove wishlist entries referencing the user
        wishlistRepository.deleteByUser_UserId(id);

        // Remove user addresses referencing the user
        List<com.example.bookstore.entity.UserAddress> addrs = userAddressRepository.findByUserUserId(id);
        if (addrs != null && !addrs.isEmpty()) {
            userAddressRepository.deleteAll(addrs);
        }

        // Finally delete the user
        userRepository.deleteById(id);
    }
}
