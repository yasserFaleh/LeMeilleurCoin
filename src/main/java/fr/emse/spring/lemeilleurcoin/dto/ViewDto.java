package fr.emse.spring.lemeilleurcoin.dto;


import fr.emse.spring.lemeilleurcoin.model.View;
import fr.emse.spring.lemeilleurcoin.model.ViewStatus;

public class ViewDto {
    private Long id;
    private String description;
    private ViewStatus viewStatus;
    private String emailViewer;
    private String emailViewedUser;

    public ViewDto() {
    }

    public ViewDto(View view) {
        this.id = view.getId();
        this.description = view.getDescription();
        this.viewStatus = view.getViewStatus();
        this.emailViewer = view.getViewer().getEmail();
        this.emailViewedUser = view.getViewedUser().getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ViewStatus getViewStatus() {
        return viewStatus;
    }

    public void setViewStatus(ViewStatus viewStatus) {
        this.viewStatus = viewStatus;
    }

    public String getEmailViewer() {
        return emailViewer;
    }

    public void setEmailViewer(String emailViewer) {
        this.emailViewer = emailViewer;
    }

    public String getEmailViewedUser() {
        return emailViewedUser;
    }

    public void setEmailViewedUser(String emailViewedUser) {
        this.emailViewedUser = emailViewedUser;
    }
}
