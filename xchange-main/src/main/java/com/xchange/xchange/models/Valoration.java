package com.xchange.xchange.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "valoration")
public class Valoration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long valorationId;

    @Column(name = "mark", nullable = false)
    private int mark;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "comment", nullable = false, length = 200)
    private String comment;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
