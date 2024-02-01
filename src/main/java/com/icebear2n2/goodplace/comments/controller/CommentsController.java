package com.icebear2n2.goodplace.comments.controller;

import com.icebear2n2.goodplace.comments.service.CommentsService;
import com.icebear2n2.goodplace.domain.dto.CommentsDto;
import com.icebear2n2.goodplace.domain.request.CreateCommentRequest;
import com.icebear2n2.goodplace.domain.request.UpdateCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentsController {
    private final CommentsService commentsService;

    // TODO: 2/1/24 Create Comments Controller

    @PostMapping
    public CommentsDto createComment(@RequestBody CreateCommentRequest createCommentRequest) {
        return commentsService.createComments(createCommentRequest);
    }

    @GetMapping("/all/{userId}")
    public Page<CommentsDto> getAll(@PathVariable Long userId,
                                    @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                                    @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {

        PageRequest pageRequest = PageRequest.of(size, page);
        return commentsService.getAllByUser(userId, pageRequest);
    }

    @PutMapping("{commentsId}")
    public CommentsDto updateComments(@PathVariable Long commentsId, @RequestBody UpdateCommentRequest updateCommentRequest) {
        return commentsService.updateComments(commentsId, updateCommentRequest);
    }

    @DeleteMapping("/{commentsId}")
    public void deleteComments(@PathVariable Long commentsId) { commentsService.deleteComment(commentsId); }
}
