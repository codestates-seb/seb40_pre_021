package com.pre21.service;

import com.pre21.entity.Bookmark;
import com.pre21.entity.User;
import com.pre21.exception.BusinessLogicException;
import com.pre21.exception.ExceptionCode;
import com.pre21.repository.AuthRepository;
import com.pre21.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {
    private final AuthRepository authRepository;

    /**
     * 
     * @param userId
     * @return
     */
    public List<Bookmark> findMyBookmarks(Long userId) {
        User findUser = findIfExistUser(userId);
        return findUser.getBookmarks();
    }


    public User findIfExistUser(Long userId) {
        return authRepository.findById(userId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }
}
