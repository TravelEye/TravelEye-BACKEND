package com.service.traveleye.domain.restaurant.controller;

import com.service.traveleye.domain.member.entity.MemberDetails;
import com.service.traveleye.domain.restaurant.dto.FavoriteReqDTO;
import com.service.traveleye.domain.restaurant.entity.Favorite;
import com.service.traveleye.domain.restaurant.service.FavoriteService;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food/favorites")
public class FavoriteController {
private final FavoriteService favoriteService;
    @GetMapping("")
    public DataResDTO<?> getAllFavorites(@AuthenticationPrincipal MemberDetails memberDetails){
        return favoriteService.getAllFavorites(memberDetails.getMember());
    }

    @PostMapping("")
    public DataResDTO<?> selectFavorite(@AuthenticationPrincipal MemberDetails memberDetails, @RequestBody FavoriteReqDTO favoriteReqDTO){
        return favoriteService.selectFavorite(memberDetails.getMember(),favoriteReqDTO.getRestaurantId(),favoriteReqDTO.getRestaurantName());
    }

    @DeleteMapping("/{id}")
    public DataResDTO<?> deleteFavorite(@AuthenticationPrincipal MemberDetails memberDetails, @PathVariable Long id){
        return favoriteService.deleteFavorite(id);
    }
}
