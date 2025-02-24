package org.example2;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "projektas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Projektas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String pavadinimas;

    @ManyToMany
    @JoinTable(
            name = "darbuotojas_projektas",
            joinColumns = @JoinColumn(name = "projektas_id"),
            inverseJoinColumns = @JoinColumn(name = "darbuotojas_id")
    )
    private List<Darbuotojas> darbuotojai;

    public Projektas(String pavadinimas, List<Darbuotojas> darbuotojai) {
        this.pavadinimas = pavadinimas;
        this.darbuotojai = darbuotojai;
    }
}
