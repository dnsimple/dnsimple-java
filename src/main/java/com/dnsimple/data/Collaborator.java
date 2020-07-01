package com.dnsimple.data;

public class Collaborator {
    private final Integer id;
    private final Integer domainId;
    private final String domainName;
    private final Integer userId;
    private final String userEmail;
    private final Boolean invitation;
    private final String createdAt;
    private final String updatedAt;

    public Collaborator(Integer id, Integer domainId, String domainName, Integer userId, String userEmail, Boolean invitation, String createdAt, String updatedAt) {
        this.id = id;
        this.domainId = domainId;
        this.domainName = domainName;
        this.userId = userId;
        this.userEmail = userEmail;
        this.invitation = invitation;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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
