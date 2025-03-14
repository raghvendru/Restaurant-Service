package com.jagcoder.Restaurant.Listing.service;

import com.jagcoder.Restaurant.Listing.dto.RestaurantDTO;
import com.jagcoder.Restaurant.Listing.entity.Restaurant;
import com.jagcoder.Restaurant.Listing.repo.RestaurantRepo;
import com.jagcoder.Restaurant.Listing.mapper.RestaurantMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepo restaurantRepo;

    @Mock
    private RestaurantMapper restaurantMapper;

    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    public void testFetchRestaurantById_ExistingId() {
        Integer mockRestaurantId = 1;
        Restaurant mockRestaurant = new Restaurant(1, "Restaurant 1", "Address 1", "City 1", "Desc 1");
        RestaurantDTO mockRestaurantDTO = new RestaurantDTO(1, "Restaurant 1", "Address 1", "City 1", "Desc 1");

        when(restaurantRepo.findById(mockRestaurantId)).thenReturn(Optional.of(mockRestaurant));
        when(restaurantMapper.mapRestaurantToRestaurantDTO(mockRestaurant)).thenReturn(mockRestaurantDTO);

        // Call the actual method
        ResponseEntity<RestaurantDTO> response = restaurantService.fetchRestaurantById(mockRestaurantId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockRestaurantDTO, response.getBody());

        verify(restaurantRepo, times(1)).findById(mockRestaurantId);
        verify(restaurantMapper, times(1)).mapRestaurantToRestaurantDTO(mockRestaurant);
    }

    @Test
    public void testFetchRestaurantById_NonExistingId() {
        Integer mockRestaurantId = 2;

        when(restaurantRepo.findById(mockRestaurantId)).thenReturn(Optional.empty());

        ResponseEntity<RestaurantDTO> response = restaurantService.fetchRestaurantById(mockRestaurantId);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());

        verify(restaurantRepo, times(1)).findById(mockRestaurantId);
        verify(restaurantMapper, never()).mapRestaurantToRestaurantDTO(any());
    }
}
