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
@Table(name = "shoes_warehouse")
@Builder
public class ShoesWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shoes_id")
    private Shoes shoes;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private ShoeSize size;

    @Column(nullable = false)
    private Integer amount;

    @OneToMany(mappedBy = "shoesWarehouse", cascade = CascadeType.ALL)
    private List<OrderShoes> orderShoesList;

    @OneToMany(mappedBy = "shoesWarehouse", cascade = CascadeType.ALL)
    private List<CartShoes> cartShoesList;
}
