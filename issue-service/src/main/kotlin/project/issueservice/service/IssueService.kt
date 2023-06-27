package project.issueservice.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import project.issueservice.domain.Issue
import project.issueservice.domain.IssueRepository
import project.issueservice.domain.IssueStatus
import project.issueservice.exception.NotFoundException
import project.issueservice.model.IssueRequest
import project.issueservice.model.IssueResponse

@Service
class IssueService (
    private val issueRepository: IssueRepository,
){

    @Transactional
    fun create(userId: Long, request: IssueRequest): IssueResponse {
        val issue = Issue (
            summary = request.summary,
            description = request.description,
            userId = userId,
            type = request.type,
            priority = request.priority,
            status = request.status,
        )

        return IssueResponse(issueRepository.save(issue))
    }

    @Transactional(readOnly = true)
    fun getAll(status: IssueStatus) =
        issueRepository.findAllByStatusOrderByCreatedAtDesc(status)
            ?.map { IssueResponse(it) }

    @Transactional(readOnly = true)
    fun get(id: Long): IssueResponse {
        val issue = issueRepository.findByIdOrNull(id) ?: throw NotFoundException("이슈가 존재하지 않습니다.")
        return IssueResponse(issue)
    }

}