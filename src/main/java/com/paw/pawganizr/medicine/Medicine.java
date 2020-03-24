package com.paw.pawganizr.medicine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paw.pawganizr.pet.Pet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "medicines")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "medicine_name")
    @NotNull
    @Length(min = 2)
    private String name;

//    private MedicineType type;

    @Column(name = "dosage")
    private String dosage;

    @Column(name = "importancy")
    @Enumerated(EnumType.STRING)
    private MedicineImportancy importancy;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
    /**
     * optional
     */
//    private LocalDate treatmentStartDate;
//    private LocalDate treatmentEndDate;
}