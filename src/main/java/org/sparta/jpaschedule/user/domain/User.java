package org.sparta.jpaschedule.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.sparta.jpaschedule.common.domain.BaseTimestampEntity;
import org.sparta.jpaschedule.usersschedules.entity.UsersScheduls;
import org.sparta.jpaschedule.user.dto.request.UserSaveDto;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseTimestampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, name = "password")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    List<UsersScheduls> usersScheduls = new ArrayList<>();

    public User(UserSaveDto userSaveDto) {
        this.userName = userSaveDto.getUserName();
        this.email = userSaveDto.getEmail();
        this.password = userSaveDto.getPassword();
    }

    // BaseTimestampEntity 를 상속받아 공통으로 처리
    // id  컬럼 다음으로 생성일, 수정일, 작성자, 댓글 순으로 컬럼이 생성됨
    // 해당 순서를 바꾸는 방법을 찾아봤지만 없음
    // 저모양이 너무 꼴보기 싫어서 공통처리는 안하기로함

    // 해당 엔티티에 넣어도 순서가 보장이 안됨 알아본 바로는 JPA와 Hibernate 는 순서를 보장하지 않음
    // 그냥 원래대로 공통처리하고 직접 순서를 바꾸기로 결정
}
