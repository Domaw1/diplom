package ru.degree.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.degree.shop.DTO.order.OrderItemGetDto;
import ru.degree.shop.model.Order;
import ru.degree.shop.model.OrderItem;

@Mapper(componentModel = "spring", uses = {ProductVariantMapper.class})
public interface OrderItemMapper {

    @Mapping(target = "order", source = "order", qualifiedByName = "mapOrder")
    OrderItemGetDto orderItemToOrderItemGetDto(OrderItem orderItem);

    @Named("mapOrder")
    default Long mapOrder(Order order) {
        return order.getId();
    }
}
