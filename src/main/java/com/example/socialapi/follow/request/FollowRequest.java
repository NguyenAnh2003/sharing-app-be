package com.example.socialapi.follow.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowRequest {
    private String userId, followingUserId;
}
