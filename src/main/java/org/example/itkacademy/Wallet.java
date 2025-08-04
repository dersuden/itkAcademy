package org.example.itkacademy;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "wallets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    @Id
    private UUID id;

    @Column(nullable = false)
    private Long balance = 0L;

    @Version
    private Long version;
}