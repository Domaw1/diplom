package ru.degree.shop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @Transient
    public Double getTotal() {
        if( orderItems == null || orderItems.isEmpty()) {
            return 0.0;
        }

        return orderItems
                .stream()
                .mapToDouble(item -> {
                    BigDecimal multiply = item.getProductVariant().getProduct().getPrice().multiply(new BigDecimal(item.getQuantity()));
                    return multiply.setScale(1, RoundingMode.HALF_UP).doubleValue();
                }).sum();
    }

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @CreationTimestamp
    @JoinColumn(updatable = false)
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
