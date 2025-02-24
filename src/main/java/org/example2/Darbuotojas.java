package org.example2;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "darbuotojas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Darbuotojas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String vardas;
    private String pavarde;
    private String pareigos;

    @ManyToOne
    @JoinColumn(name = "skyrius_id")
    private Skyrius skyrius;

    public Darbuotojas(String vardas, String pavarde, String pareigos, Skyrius skyrius) {
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.pareigos = pareigos;
        this.skyrius = skyrius;
    }
}

