package com.pre21.repository;

import com.pre21.entity.Bookmark;
import com.pre21.entity.Questions;
import com.pre21.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findBookmarksByUsersAndQuestions(User user, Questions questions);

}
