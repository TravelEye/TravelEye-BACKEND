package com.service.traveleye.domain.restaurant.service;


import com.service.traveleye.domain.member.entity.Member;
import com.service.traveleye.global.dto.DataResDTO;

public interface FavoriteService {
    DataResDTO<?> getAllFavorites(Member member);
    DataResDTO<?> selectFavorite(Member member, String restaurantId, String restaurantName);
    DataResDTO<?> deleteFavorite(Long favoriteId);
}
