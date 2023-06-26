package project.issueservice.web

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import project.issueservice.config.AuthUser
import project.issueservice.model.IssueRequest
import project.issueservice.service.IssueService

@RestController
@RequestMapping("/api/v1/issues")
class IssueController(
    private val issueService: IssueService,
) {

    @PostMapping
    fun create(
        authUser: AuthUser,
        @RequestBody request: IssueRequest
    ) = issueService.create(authUser.userId, request)
}