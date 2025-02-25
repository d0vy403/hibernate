package org.example2;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
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

    @OneToMany(mappedBy = "projektas", cascade = CascadeType.ALL)
    private List<Darbuotojas> darbuotojai;

    public Projektas(String pavadinimas, List<Darbuotojas> darbuotojai) {
        this.pavadinimas = pavadinimas;
        this.darbuotojai = darbuotojai;
    }
}
