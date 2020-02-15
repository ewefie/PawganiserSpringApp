package com.paw.pawganizr.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paw.pawganizr.enums.PetGender;
import com.paw.pawganizr.enums.PetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;

@Entity(name = "pets")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    /**
     * some of details will be mandatory:
     */
    @Column(name = "pet_name")
    @Length(min = 2)
    @NotNull
    private String petName;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @NotNull
    private PetType type;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AppUser owner;

    /**
     * *******************************
     * rest of details will be optional
     */
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private PetGender gender;

    @Column(name = "chip_number")
    private String chipNumber; //chip number

    @Column(name = "avatar_url")
    private String petAvatarUrl;

    @Column(name = "coat_color")
    private String color;

    @JsonIgnore
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medicine> medicines;

    @JsonIgnore
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Treatment> treatments;

    @JsonIgnore
    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nutrition> nutrition;

    @Column(name = "birth_name")
    private LocalDate birthDate;
    /**
     * only after checking "pet is dead" option, input for death date should be shown
     */
    @Column(name = "dead")
    private boolean dead;

    @Column(name = "death_date")
    private LocalDate deathDate;

    /**
     * *************************************************************************************************************** *
     * additional details only for pets having pedigree, inputs will be shown only after checking "have pedigree" option.
     */
    @Column(name = "race")
    private String race;

    //    @JsonIgnore
//    @OneToOne(mappedBy = "pet", cascade = CascadeType.ALL)
    @OneToOne
    @JoinColumn(name = "pedigree_id")
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    private Pedigree pedigree;

    @AssertTrue
    @JsonIgnore
    private boolean isDeathDateValid() {
        return (!dead && isNull(deathDate)) || deathDate.isAfter(birthDate);
    }

    @AssertTrue
    @JsonIgnore
    private boolean isChipNumberValid() {
        return isNull(chipNumber) || chipNumber.length() == 15 || chipNumber.length() == 0;
    }
}
