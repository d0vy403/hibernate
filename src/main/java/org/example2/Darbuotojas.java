package org.example2;

import jakarta.persistence.*;
import lombok.*;

@Entity
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

    @ManyToOne
    @JoinColumn(name = "projektas_id")
    private Projektas projektas;

    public Darbuotojas(String vardas, String pavarde, String pareigos, Skyrius skyrius, Projektas projektas) {
        this.vardas = vardas;
        this.pavarde = pavarde;
        this.pareigos = pareigos;
        this.skyrius = skyrius;
        this.projektas = projektas;
    }
}

