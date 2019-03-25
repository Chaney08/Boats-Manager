package org.opentech.boatsmanager.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "boat")
public class Boat {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long boatId;

    @NotBlank(message = "Boat Name is mandatory")
    private String boatName;
    @NotBlank(message = "Description is mandatory")
    private String description;
    @NotNull(message = "Weight is mandatory")
    private Double weight;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;


    public Long getBoatId() {
        return boatId;
    }

    public void setBoatId(Long boatId) {
        this.boatId = boatId;
    }

    public String getBoatName() {
        return boatName;
    }

    public void setBoatName(String boatName) {
        this.boatName = boatName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Boat{" +
                "boatId=" + boatId +
                ", boatName='" + boatName + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", createDateTime=" + createDateTime +
                '}';
    }
}
