package com.icebear2n2.goodplace.recommend.service;

import com.icebear2n2.goodplace.domain.dto.RecommendDto;
import com.icebear2n2.goodplace.domain.entity.Recommend;
import com.icebear2n2.goodplace.domain.entity.Store;
import com.icebear2n2.goodplace.domain.entity.User;
import com.icebear2n2.goodplace.domain.repository.RecommendRepository;
import com.icebear2n2.goodplace.domain.repository.StoreRepository;
import com.icebear2n2.goodplace.domain.repository.UserRepository;
import com.icebear2n2.goodplace.domain.request.RecommendRequest;
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
public class RecommendService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecommendService.class);
    private final RecommendRepository recommendRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    // TODO: 2/1/24 Recommend CRD;

    public RecommendDto connectionRecommend(RecommendRequest recommendRequest) {
        try {
            User user = userRepository.findById(recommendRequest.getUserId()).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));
            Store store = storeRepository.findById(recommendRequest.getStoreId()).orElseThrow(() -> new ServiceException(ErrorCode.STORE_NOT_FOUND));

            Recommend savedRecommend = recommendRequest.toEntity(user, store);
            return new RecommendDto(savedRecommend);

        } catch (Exception e) {
            LOGGER.error("Error creating recommend: " + e.getMessage());
            throw new ServiceException(ErrorCode.RECOMMEND_CREATION_FAILED);
        }
    }

    public Page<RecommendDto> getAllByUser(Long userId, PageRequest pageRequest) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));
            Page<Recommend> allByUser = recommendRepository.findAllByUser(user, pageRequest);

            return allByUser.map(RecommendDto::new);
        } catch (Exception e) {
            LOGGER.error("Error retrieving recommend: " + e.getMessage());
            throw new ServiceException(ErrorCode.RECOMMEND_RETRIEVAL_FAILED);
        }
    }

    public void deleteRecommend(Long recommendId) {
        try {

        Recommend exsitingRecommend = recommendRepository.findById(recommendId).orElseThrow(() -> new ServiceException(ErrorCode.RECOMMEND_NOT_FOUND));

        recommendRepository.delete(exsitingRecommend);
        } catch (Exception e) {
            LOGGER.error("Error deleting recommend: " + e.getMessage());
            throw new ServiceException(ErrorCode.RECOMMEND_DELETION_FAILED);
        }
    }
}
