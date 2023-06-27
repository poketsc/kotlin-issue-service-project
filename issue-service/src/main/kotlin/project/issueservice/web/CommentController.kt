package project.issueservice.web

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import project.issueservice.config.AuthUser
import project.issueservice.model.CommentRequest
import project.issueservice.model.CommentResponse
import project.issueservice.service.CommentService

@RestController
@RequestMapping("/api/v1/issues/{issueId}/comments")
class CommentController (
    private val commentService: CommentService,
){

    @PostMapping
    fun create(
        authUser: AuthUser,
        @PathVariable issueId: Long,
        @RequestBody request: CommentRequest,
    ) : CommentResponse {
        return commentService.create(issueId, authUser.userId, authUser.userName, request)
    }

    @PutMapping("/{id}")
    fun edit(
        authUser: AuthUser,
        @PathVariable id: Long,
        @RequestBody request: CommentRequest
    ) = commentService.edit(id, authUser.userId, request)
}