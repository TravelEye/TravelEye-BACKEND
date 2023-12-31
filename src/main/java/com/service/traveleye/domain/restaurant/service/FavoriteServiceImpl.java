package com.service.traveleye.domain.restaurant.service;

import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.domain.member.repository.MemberRepository;
import com.service.traveleye.domain.restaurant.entity.Favorite;
import com.service.traveleye.domain.restaurant.repository.FavoriteRepository;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService{

    private final FavoriteRepository favoriteRepository;
    @Override
    public DataResDTO<?> getAllFavorites(Member member) {
        return DataResDTO.builder()
                .message("모든 찜목록 가져오기 완료")
                .data(favoriteRepository.findFavoritesByMember(member))
                .build();
    }

    @Override
    public DataResDTO<?> selectFavorite(Member member, String restaurantId,String restaurantName) {
        Favorite favorite = Favorite.builder()
                .restaurantId(restaurantId)
                .restaurantName(restaurantName)
                .member(member)
                .build();

        return DataResDTO.builder()
                .message("찜하기 완료")
                .data(favoriteRepository.save(favorite))
                .build();
    }

    @Override
    @Transactional
    public DataResDTO<?> deleteFavorite(Long favoriteId) {
        return DataResDTO.builder()
                .message("찜취소 완료")
                .data(favoriteRepository.deleteFavoriteById(favoriteId))
                .build();
    }
}
