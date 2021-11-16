package fr.emse.spring.lemeilleurcoin.model;

import javax.persistence.*;

@Entity
public class View {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ViewStatus viewStatus;



    @ManyToOne
    private User viewer;

    @ManyToOne
    private User viewedUser;

    public View(Long id, String description, ViewStatus viewStatus, User viewer, User viewedUser) {
        this.id = id;
        this.description = description;
        this.viewStatus = viewStatus;
        this.viewer = viewer;
        this.viewedUser = viewedUser;
    }

    public View() {
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

    public User getViewer() {
        return viewer;
    }

    public void setViewer(User viewer) {
        this.viewer = viewer;
    }

    public User getViewedUser() {
        return viewedUser;
    }

    public void setViewedUser(User viewedUser) {
        this.viewedUser = viewedUser;
    }
}
