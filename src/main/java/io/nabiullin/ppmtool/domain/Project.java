package io.nabiullin.ppmtool.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;

    private String projectIdentifier;

    private String discription ;

    private String start_date;

    private String end_date;


    private Date created_At;

    private Date updated_At;

    public Project() {
    }

    public Project(String projectName,
                   String projectIdentifier,
                   String discription,
                   String start_date,
                   String end_date,
                   Date created_At,
                   Date updated_At) {
        this.projectName = projectName;
        this.projectIdentifier = projectIdentifier;
        this.discription = discription;
        this.start_date = start_date;
        this.end_date = end_date;
        this.created_At = created_At;
        this.updated_At = updated_At;
    }

    @PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updated_At = new Date();
    }
}
