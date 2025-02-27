
package com.jagcoder.Restaurant.Listing.service;

import com.jagcoder.Restaurant.Listing.dto.RestaurantDTO;
import com.jagcoder.Restaurant.Listing.entity.Restaurant;
import com.jagcoder.Restaurant.Listing.mapper.RestaurantMapper;
import com.jagcoder.Restaurant.Listing.repo.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private RestaurantMapper restaurantMapper;

    public List<RestaurantDTO> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
        return restaurants.stream()
                .map(restaurant -> restaurantMapper.mapRestaurantToRestaurantDTO(restaurant))
                .collect(Collectors.toList());
    }

    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
        Restaurant savedRestaurant = restaurantRepo.save(restaurantMapper.mapRestaurantDTOToRestaurant(restaurantDTO));
        return restaurantMapper.mapRestaurantToRestaurantDTO(savedRestaurant);
    }

    public ResponseEntity<RestaurantDTO> fetchRestaurantById(Integer id) {
        Optional<Restaurant> restaurant = restaurantRepo.findById(id);
        if(restaurant.isPresent()){
            return new ResponseEntity<>(restaurantMapper.mapRestaurantToRestaurantDTO(restaurant.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}