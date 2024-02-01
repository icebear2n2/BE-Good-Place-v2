package com.icebear2n2.goodplace.store.service;

import com.icebear2n2.goodplace.domain.dto.StoreDto;
import com.icebear2n2.goodplace.domain.entity.Store;
import com.icebear2n2.goodplace.domain.repository.StoreRepository;
import com.icebear2n2.goodplace.domain.request.StoreRequest;
import com.icebear2n2.goodplace.exception.ErrorCode;
import com.icebear2n2.goodplace.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StoreService.class);
    private final StoreRepository storeRepository;

    // TODO: 2/1/24 Store CRUD

    public StoreDto insertStore(StoreRequest storeRequest) {
        try {
            Store store = storeRequest.toEntity(storeRequest.getAddress(), storeRequest.getCoordinateX(), storeRequest.getCoordinateY(), storeRequest.getFoodType(), storeRequest.getMyReview(), storeRequest.getStoreName(), storeRequest.getStorePhone(), storeRequest.getPlaceId());

            Store savedStore = storeRepository.save(store);
            return new StoreDto(savedStore);
        } catch (Exception e) {
            LOGGER.error("Error inserting store: " + e.getMessage());
            throw new ServiceException(ErrorCode.REVIEW_CREATION_FAILED);
        }
    }

    public Page<StoreDto> getAll(PageRequest pageRequest) {
        try {
            Page<Store> all = storeRepository.findAll(pageRequest);
            return all.map(StoreDto::new);
        } catch (Exception e) {
            LOGGER.error("Error retrieving stores: " + e.getMessage());
            throw new ServiceException(ErrorCode.STORE_RETRIEVAL_FAILED);
        }
    }

    public StoreDto updateStore(Long storeId, StoreRequest storeRequest) {
        try {
            Store existingStore = storeRepository.findById(storeId).orElseThrow(() -> new ServiceException(ErrorCode.STORE_NOT_FOUND));

            if (storeRequest.getAddress() != null) {
                existingStore.setAddress(storeRequest.getAddress());
            }

            if (storeRequest.getCoordinateX() != null) {
                existingStore.setCoordinateX(storeRequest.getCoordinateX());
            }

            if (storeRequest.getCoordinateY() != null) {
                existingStore.setCoordinateY(storeRequest.getCoordinateY());
            }

            if (storeRequest.getFoodType() != null) {
                existingStore.setFoodType(storeRequest.getFoodType());
            }

            if (storeRequest.getMyReview() != null) {
                existingStore.setMyReview(storeRequest.getMyReview());
            }

            if (storeRequest.getStoreName() != null) {
                existingStore.setStoreName(storeRequest.getStoreName());
            }

            if (storeRequest.getStorePhone() != null) {
                existingStore.setStorePhone(storeRequest.getStorePhone());
            }
            if (storeRequest.getPlaceId() != null) {
                existingStore.setPlaceId(storeRequest.getPlaceId());
            }

            Store savedStore = storeRepository.save(existingStore);
            return new StoreDto(savedStore);
        } catch (Exception e) {
            LOGGER.error("Error updating store: " + e.getMessage());
            throw new ServiceException(ErrorCode.STORE_UPDATE_FAILED);
        }
    }

    public void deleteStore(Long storeId) {
        try {
            Store exsitingStore = storeRepository.findById(storeId).orElseThrow(() -> new ServiceException(ErrorCode.STORE_NOT_FOUND));
            storeRepository.delete(exsitingStore);
        } catch (Exception e) {
            LOGGER.error("Error deleting store: " + e.getMessage());
            throw new ServiceException(ErrorCode.STORE_DELETION_FAILED);
        }
    }
}
