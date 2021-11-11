package fr.emse.spring.lemeilleurcoin.model;

import javax.persistence.*;

@Entity
public class View {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private User viewer;

    @ManyToOne
    private User viewedUser;
}
