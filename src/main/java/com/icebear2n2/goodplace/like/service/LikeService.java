package com.icebear2n2.goodplace.like.service;

import com.icebear2n2.goodplace.domain.dto.LikeDto;
import com.icebear2n2.goodplace.domain.dto.RecommendDto;
import com.icebear2n2.goodplace.domain.entity.Like;
import com.icebear2n2.goodplace.domain.entity.Recommend;
import com.icebear2n2.goodplace.domain.entity.Store;
import com.icebear2n2.goodplace.domain.entity.User;
import com.icebear2n2.goodplace.domain.repository.LikeRepository;
import com.icebear2n2.goodplace.domain.repository.RecommendRepository;
import com.icebear2n2.goodplace.domain.repository.StoreRepository;
import com.icebear2n2.goodplace.domain.repository.UserRepository;
import com.icebear2n2.goodplace.domain.request.LikeRequest;
import com.icebear2n2.goodplace.domain.request.RecommendRequest;
import com.icebear2n2.goodplace.exception.ErrorCode;
import com.icebear2n2.goodplace.exception.ServiceException;
import com.icebear2n2.goodplace.recommend.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LikeService.class);
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public LikeDto connectionLike(LikeRequest likeRequest) {
        try {
            User user = userRepository.findById(likeRequest.getUserId()).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));
            Store store = storeRepository.findById(likeRequest.getStoreId()).orElseThrow(() -> new ServiceException(ErrorCode.STORE_NOT_FOUND));

            Like savedLike = likeRequest.toEntity(user, store);
            return new LikeDto(savedLike);

        } catch (Exception e) {
            LOGGER.error("Error creating like: " + e.getMessage());
            throw new ServiceException(ErrorCode.LIKE_CREATION_FAILED);
        }
    }

    public Page<LikeDto> getAllByUser(Long userId, PageRequest pageRequest) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));
            Page<Like> allByUser = likeRepository.findAllByUser(user, pageRequest);

            return allByUser.map(LikeDto::new);
        } catch (Exception e) {
            LOGGER.error("Error retrieving like: " + e.getMessage());
            throw new ServiceException(ErrorCode.LIKE_RETRIEVAL_FAILED);
        }
    }

    public void deleteLike(Long likeId) {
        try {

            Like existingRecommend = likeRepository.findById(likeId).orElseThrow(() -> new ServiceException(ErrorCode.LIKE_NOT_FOUND));

            likeRepository.delete(existingRecommend);
        } catch (Exception e) {
            LOGGER.error("Error deleting like: " + e.getMessage());
            throw new ServiceException(ErrorCode.LIKE_DELETION_FAILED);
        }
    }
}
