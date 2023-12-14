package com.example.socialapi.follow;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/follow")
@Tag(name = "Follow")
public class FollowController {
    private final FollowService followService;

}
