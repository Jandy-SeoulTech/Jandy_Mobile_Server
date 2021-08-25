package com.jandy.plogging.repository;

import com.jandy.plogging.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Member> findMemberByEmail(String email);

}
