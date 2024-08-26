package org.sparta.jpaschedule.usersschedules.entity;


import jakarta.persistence.*;
import lombok.*;
import org.sparta.jpaschedule.schedule.entity.Schedule;
import org.sparta.jpaschedule.user.entity.User;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UsersScheduls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

}
