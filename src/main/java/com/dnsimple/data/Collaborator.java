package com.dnsimple.data;

public class Collaborator {
    private Integer id;
    private Integer domainId;
    private String domainName;
    private Integer userId;
    private String userEmail;
    private Boolean invitation;
    private String createdAt;
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public String getDomainName() {
        return domainName;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Boolean getInvitation() {
        return invitation;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
