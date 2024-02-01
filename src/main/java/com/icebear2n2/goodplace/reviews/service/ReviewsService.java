package com.icebear2n2.goodplace.reviews.service;

import com.icebear2n2.goodplace.domain.dto.ReviewsDto;
import com.icebear2n2.goodplace.domain.entity.Reviews;
import com.icebear2n2.goodplace.domain.entity.Store;
import com.icebear2n2.goodplace.domain.entity.User;
import com.icebear2n2.goodplace.domain.repository.ReviewsRepository;
import com.icebear2n2.goodplace.domain.repository.StoreRepository;
import com.icebear2n2.goodplace.domain.repository.UserRepository;
import com.icebear2n2.goodplace.domain.request.ReviewRequest;
import com.icebear2n2.goodplace.domain.request.UpdateReviewRequest;
import com.icebear2n2.goodplace.exception.ErrorCode;
import com.icebear2n2.goodplace.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewsService.class);

    private final ReviewsRepository reviewsRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    // TODO: 2/1/24 Review CRUD

    public ReviewsDto createReviews(ReviewRequest reviewRequest) {
        try {
            Store store = storeRepository.findById(reviewRequest.getStoreId()).orElseThrow(() -> new ServiceException(ErrorCode.STORE_NOT_FOUND));
            User user = userRepository.findById(reviewRequest.getUserId()).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));

            Reviews reviews = reviewRequest.toEntity(store, user, reviewRequest.getReviewTitle(), reviewRequest.getReviewContent());

            Reviews savedReviews = reviewsRepository.save(reviews);
            return new ReviewsDto(savedReviews);
        } catch (Exception e) {
            LOGGER.error("Error creating review: " + e.getMessage());
            throw new ServiceException(ErrorCode.REVIEW_CREATION_FAILED);
        }


    }

    public Page<ReviewsDto> getAllByUser(Long userId, PageRequest pageRequest) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));
            Page<Reviews> allByUser = reviewsRepository.findAllByUser(user, pageRequest);

            return allByUser.map(ReviewsDto::new);
        } catch (Exception e) {
            LOGGER.error("Error retrieving reviews: " + e.getMessage());
            throw new ServiceException(ErrorCode.REVIEW_RETRIEVAL_FAILED);
        }
    }

    public ReviewsDto updatedReviews(Long reviewsId, UpdateReviewRequest updateReviewRequest) {
        try {
            Reviews existingReviews = reviewsRepository.findById(reviewsId).orElseThrow(() -> new ServiceException(ErrorCode.REVIEW_NOT_FOUND));

        if (updateReviewRequest.getReviewTitle() != null) {
            existingReviews.setReviewTitle(updateReviewRequest.getReviewTitle());
        }

        if (updateReviewRequest.getReviewContent() != null) {
            existingReviews.setReviewContent(updateReviewRequest.getReviewContent());
        }

        Reviews savedReviews = reviewsRepository.save(existingReviews);
        return new ReviewsDto(savedReviews);

    }catch (Exception e) {
            LOGGER.error("Error updating review: " + e.getMessage());
            throw new ServiceException(ErrorCode.REVIEW_UPDATE_FAILED);
        }
    }

    public void deleteReviews(Long reviewsId) {
        try {
            Reviews existingReviews = reviewsRepository.findById(reviewsId).orElseThrow(() -> new ServiceException(ErrorCode.REVIEW_NOT_FOUND));

            reviewsRepository.delete(existingReviews);
        } catch (Exception e) {
            LOGGER.error("Error deleting review: " + e.getMessage());
            throw new ServiceException(ErrorCode.REVIEW_DELETION_FAILED);
        }

    }
}
