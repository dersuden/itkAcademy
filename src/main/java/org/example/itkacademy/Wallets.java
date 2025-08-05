package org.example.itkacademy;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "wallets")
public class Wallets {
    @Id
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private Long balance = 0L;

    @Version
    private Long version;

    public Wallets() {
    }

    public Wallets(UUID id) {
        this.id = id;
    }
}