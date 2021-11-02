package seoultech.gdsc.web.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Liked {
    @Id //primary key!
    @Column(nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id 니까 자동으로 1씩 증가하게끔!
    private int id;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @Column(nullable = false)
    private int likeCategory;

    @Column(nullable = false)
    private int refId;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();



}
