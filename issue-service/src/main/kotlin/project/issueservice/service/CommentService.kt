package project.issueservice.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import project.issueservice.domain.Comment
import project.issueservice.domain.CommentRepository
import project.issueservice.domain.IssueRepository
import project.issueservice.exception.NotFoundException
import project.issueservice.model.CommentRequest
import project.issueservice.model.CommentResponse
import project.issueservice.model.toResponse

@Service
class CommentService (
    private val commentRepository: CommentRepository,
    private val issueRepository: IssueRepository,
){

    @Transactional
    fun create(issueId: Long, userId: Long, userName: String, request: CommentRequest) : CommentResponse {

        val issue = issueRepository.findByIdOrNull(issueId) ?: throw NotFoundException("이슈가 존재하지 않습니다.")

        val comment = Comment(
            issue = issue,
            userId = userId,
            userName = userName,
            body = request.body,
        )

        issue.comments.add(comment)
        return commentRepository.save(comment).toResponse()
    }
}