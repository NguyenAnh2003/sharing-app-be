package com.example.socialapi.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FollowDTO {
    /* followerId: userId
    * followingId: following user
    *  */
    private String id, followerId, followingId;
}
