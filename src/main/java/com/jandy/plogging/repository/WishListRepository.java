package com.jandy.plogging.repository;

import com.jandy.plogging.domain.Member;
import com.jandy.plogging.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    List<WishList> findWishListsByMember(Member member);

}
