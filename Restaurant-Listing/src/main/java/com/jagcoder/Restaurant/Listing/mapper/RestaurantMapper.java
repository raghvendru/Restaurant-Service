package com.jagcoder.Restaurant.Listing.mapper;

import com.jagcoder.Restaurant.Listing.dto.RestaurantDTO;
import com.jagcoder.Restaurant.Listing.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    @Mapping(target = "id", ignore = true)
    Restaurant mapRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO);

    RestaurantDTO mapRestaurantToRestaurantDTO(Restaurant restaurant);
}