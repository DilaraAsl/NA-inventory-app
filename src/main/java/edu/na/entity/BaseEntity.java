package edu.na.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false,updatable = false)
    public LocalDateTime insertDateTime;

    @Column(nullable = false,updatable = false)
    public Long insertUserId;

    @Column(nullable = false)
    public LocalDateTime lastUpdateDateTime;

    @Column(nullable = false)
    public Long lastUpdateUserId;

    private Boolean isDeleted = false;
}
