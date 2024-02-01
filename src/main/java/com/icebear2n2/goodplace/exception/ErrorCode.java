package com.icebear2n2.goodplace.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    // Common Errors
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad request."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Unauthorized access."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not found."),
    CONFLICT(HttpStatus.CONFLICT, "Resource conflict."),

    // User-specific Errors
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found."),
    DUPLICATED_USER_EMAIL(HttpStatus.CONFLICT, "User email is duplicated."),
    INVALID_CREDENTIAL(HttpStatus.UNAUTHORIZED, "Invalid credential."),
    FAILED_LOGIN(HttpStatus.BAD_REQUEST, "Your email or password does not match."),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "Permission is invalid."),

    // Authentication and Authorization
    FAILED_CREATE_TOKEN(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while creating and saving token."),
    FAILED_SEND_AUTH_CODE(HttpStatus.INTERNAL_SERVER_ERROR, "Error sending auth code."),
    INVALID_AUTH_CODE(HttpStatus.BAD_REQUEST, "Invalid auth code."),
    EXPIRED_AUTH_CODE(HttpStatus.BAD_REQUEST, "Auth code expired."),
    INVALID_PASSWORD_FORMAT(HttpStatus.BAD_REQUEST, "The password must be at least 8 characters long, including capital letters."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "Passwords do not match."),

    // Store and Reviews
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "Store not found."),
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "Review not found."),
    COMMENTS_NOT_FOUND(HttpStatus.NOT_FOUND, "Comments not found."),
    REVIEW_CREATION_FAILED(HttpStatus.BAD_REQUEST, "Review creation failed."),
    REVIEW_RETRIEVAL_FAILED(HttpStatus.BAD_REQUEST, "Review retrieval failed."),
    REVIEW_UPDATE_FAILED(HttpStatus.BAD_REQUEST, "Review update failed."),
    REVIEW_DELETION_FAILED(HttpStatus.BAD_REQUEST, "Review deletion failed."),
    COMMENTS_CREATION_FAILED(HttpStatus.BAD_REQUEST, "Comments creation failed."),
    COMMENTS_RETRIEVAL_FAILED(HttpStatus.BAD_REQUEST, "Comments retrieval failed."),
    COMMENTS_UPDATE_FAILED(HttpStatus.BAD_REQUEST, "Comments update failed."),
    COMMENTS_DELETION_FAILED(HttpStatus.BAD_REQUEST, "Comments deletion failed."),
    STORE_INSERTION_FAILED(HttpStatus.BAD_REQUEST, "Store insertion failed."),
    STORE_RETRIEVAL_FAILED(HttpStatus.BAD_REQUEST, "Store retrieval failed."),
    STORE_UPDATE_FAILED(HttpStatus.BAD_REQUEST, "Store update failed."),
    STORE_DELETION_FAILED(HttpStatus.BAD_REQUEST, "Store deletion failed."),
    ;


    private final HttpStatus httpStatus;
    private final String message;
}
