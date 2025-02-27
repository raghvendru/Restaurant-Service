package com.jagcoder.Restaurant.Listing.repo;


import com.jagcoder.Restaurant.Listing.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {
}