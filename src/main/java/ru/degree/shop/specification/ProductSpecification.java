package ru.degree.shop.specification;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import ru.degree.shop.model.Product;
import ru.degree.shop.model.ProductColor;
import ru.degree.shop.model.ProductVariant;
import ru.degree.shop.model.Size;

import java.math.BigDecimal;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> filterProduct(
            List<String> brands,
            List<String> colors,
            List<String> sizes,
            String subCategory,
            BigDecimal minPrice,
            BigDecimal maxPrice
    ) {
        return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate predicate = cb.conjunction();

            if (brands != null && !brands.isEmpty()) {
                predicate = cb.and(predicate, root.get("brand").get("name").in(brands));
            }

            Join<Product, ProductVariant> variantJoin = root.join("productVariants", JoinType.INNER);

            if (colors != null && !colors.isEmpty()) {
                Join<ProductVariant, ProductColor> colorJoin = variantJoin.join("color", JoinType.INNER);
                predicate = cb.and(predicate, colorJoin.get("color").in(colors));
            }

            if (sizes != null && !sizes.isEmpty()) {
                Join<ProductVariant, Size> sizeJoin = variantJoin.join("size", JoinType.INNER);
                predicate = cb.and(predicate, sizeJoin.get("size").in(sizes));
            }

            if(subCategory != null) {
                predicate = cb.and(predicate, cb.equal(root.get("subCategory").get("name"), subCategory));
            }

            if (minPrice != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            if (maxPrice != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            return predicate;
        };
    }
}
