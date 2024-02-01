package com.icebear2n2.goodplace.comments.service;

import com.icebear2n2.goodplace.domain.dto.CommentsDto;
import com.icebear2n2.goodplace.domain.entity.Comments;
import com.icebear2n2.goodplace.domain.entity.Reviews;
import com.icebear2n2.goodplace.domain.entity.User;
import com.icebear2n2.goodplace.domain.repository.CommentsRepository;
import com.icebear2n2.goodplace.domain.repository.ReviewsRepository;
import com.icebear2n2.goodplace.domain.repository.UserRepository;
import com.icebear2n2.goodplace.domain.request.CreateCommentRequest;
import com.icebear2n2.goodplace.domain.request.UpdateCommentRequest;
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
public class CommentsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentsService.class);

    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;
    private final ReviewsRepository reviewsRepository;

    // TODO: 2/1/24 Comment CRUD

    public CommentsDto createComments(CreateCommentRequest createCommentRequest) {
        try {
            User user = userRepository.findById(createCommentRequest.getUserId()).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));
            Reviews reviews = reviewsRepository.findById(createCommentRequest.getReviewsId()).orElseThrow(() -> new ServiceException(ErrorCode.REVIEW_NOT_FOUND));

            Comments comments = createCommentRequest.toEntity(user, reviews, createCommentRequest.getCommentContent());

            Comments savedComments = commentsRepository.save(comments);
            return new CommentsDto(savedComments);
        } catch (Exception e) {
            LOGGER.error("Error creating comment: " + e.getMessage());
            throw new ServiceException(ErrorCode.COMMENTS_CREATION_FAILED);
        }
    }

    public Page<CommentsDto> getAllByUser(Long userId, PageRequest pageRequest) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new ServiceException(ErrorCode.USER_NOT_FOUND));
            Page<Comments> allByUser = commentsRepository.findAllByUser(user, pageRequest);

            return allByUser.map(CommentsDto::new);
        } catch (Exception e) {
            LOGGER.error("Error retrieving comments: " + e.getMessage());
            throw new ServiceException(ErrorCode.COMMENTS_RETRIEVAL_FAILED);
        }
    }

    public CommentsDto updateComments(Long commentsId, UpdateCommentRequest updateCommentRequest) {
        try {
            Comments exisingComments = commentsRepository.findById(commentsId).orElseThrow(() -> new ServiceException(ErrorCode.COMMENTS_NOT_FOUND));

            if (updateCommentRequest.getCommentContent() != null) {
                exisingComments.setCommentContent(updateCommentRequest.getCommentContent());
            }

            Comments saveComments = commentsRepository.save(exisingComments);
            return new CommentsDto(saveComments);
        } catch (Exception e) {
            LOGGER.error("Error updating comment: " + e.getMessage());
            throw new ServiceException(ErrorCode.COMMENTS_UPDATE_FAILED);
        }
    }

    public void deleteComment(Long commentsId) {
        try {
            Comments comments = commentsRepository.findById(commentsId).orElseThrow(() -> new ServiceException(ErrorCode.COMMENTS_NOT_FOUND));

            commentsRepository.delete(comments);
        } catch (Exception e) {
            LOGGER.error("Error deleting comment: " + e.getMessage());
            throw new ServiceException(ErrorCode.COMMENTS_DELETION_FAILED);
        }
    }

}
