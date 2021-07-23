package com.jandy.plogging.repository;

import com.jandy.plogging.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {


}
