package com.example.footballclubapi.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clothing_warehouse")
@Builder
public class ClothingWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clothing_id")
    private Clothing clothing;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private ClothSize size;

    @Column(nullable = false)
    private Integer amount;

    @OneToMany(mappedBy = "clothingWarehouse", cascade = CascadeType.ALL)
    private List<OrderClothing> orderClothingList;

    @OneToMany(mappedBy = "clothingWarehouse", cascade = CascadeType.ALL)
    private List<CartClothing> cartClothingList;
}
