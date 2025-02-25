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
public class Skyrius {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String pavadinimas;

    @OneToMany(mappedBy = "skyrius", cascade = CascadeType.ALL)
    private List<Darbuotojas> darbuotojai;

    public Skyrius(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }
}
