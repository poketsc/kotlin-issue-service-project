package project.issueservice.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import project.issueservice.domain.Issue
import project.issueservice.domain.IssueRepository
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

}