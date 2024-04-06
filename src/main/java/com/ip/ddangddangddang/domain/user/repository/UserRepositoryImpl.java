package com.ip.ddangddangddang.domain.user.repository;

import com.ip.ddangddangddang.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public void save(User user) {
        userJpaRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return userJpaRepository.existsByNickname(nickname);
    }

    @Override
    public User findById(Long id) {
        return userJpaRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("회원이 존재하지 않습니다.")
        );
    }

    @Override
    public void delete(User user) {
        userJpaRepository.delete(user);
    }

}