package com.pets.lostpets.entity;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String nombreMascota;

    @NotNull
    @Min(0)
    @Max(30)
    private String edad;

    private boolean estado;

    private String rutaImagen;

    @Transient
    private MultipartFile imagen;

}
