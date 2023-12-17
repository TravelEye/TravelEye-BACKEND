package com.service.traveleye.domain.restaurant.repository;

import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.restaurant.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    List<Favorite> findFavoritesByMember(Member member);
    int deleteFavoriteById(Long id);

}
