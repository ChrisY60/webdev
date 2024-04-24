package com.example.webdevvideoservice.entity;

import com.example.webdevvideoservice.enums.InteractionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "interactions")
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private Long userId;
    @NonNull
    private Long videoId;
    @NonNull
    private InteractionType type;
}
