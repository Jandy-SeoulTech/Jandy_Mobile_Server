package com.jandy.plogging.repository;

import com.jandy.plogging.domain.Member;
import com.jandy.plogging.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    List<WishList> findWishListsByMember(Member member);

    @Query("delete from WishList where id in :ids")
    void deleteAllByIdInQuery(@Param("ids") List<Long> ids);

}
