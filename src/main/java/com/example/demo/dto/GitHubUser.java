package com.example.demo.dto;

import lombok.Data;

@Data
public class GitHubUser {
    private Integer id;
    private String name;
    private String bio;
    private String avatarUrl;
}
