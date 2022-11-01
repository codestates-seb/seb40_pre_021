package com.pre21.repository;

import com.pre21.entity.Adoption;
import com.pre21.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
    Optional<Adoption> findAdoptionByQuestions(Questions questions);
}
