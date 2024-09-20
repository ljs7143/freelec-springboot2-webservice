package com.jojoldu.com.freelecspringboot2webservice.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass  //JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들도 칼럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class)    //엔티티가 생성되고 수정되는 시점을 감시하여 생성일, 생성자, 수정일, 수정자 를 자동으로 기록
public abstract class BaseTimeEntity {

    @CreatedDate //entity가 생성되어 저장될 때 시간이 자동 저장된다
    private LocalDateTime createdDate;
    @LastModifiedDate //변경할 떄 시간 자동 저장
    private  LocalDateTime modifiedDate;


}
