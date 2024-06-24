package com.macwie.app;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private boolean isNew = true;

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PostLoad
    @PrePersist
    void markNotNew() {
        this.isNew = false;
    }
}
