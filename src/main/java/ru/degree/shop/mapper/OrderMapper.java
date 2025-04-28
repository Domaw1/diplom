package ru.degree.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.degree.shop.DTO.order.OrderGetDto;
import ru.degree.shop.model.Order;
import ru.degree.shop.model.User;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    @Mapping(target = "user", source = "user", qualifiedByName = "mapUser")
    OrderGetDto orderToOrderGetDto(Order order);

    List<OrderGetDto> ordersToOrderGetDtoList(List<Order> orders);

    @Named("mapUser")
    default String mapUser(User user) {
        return user.getEmail();
    }
}
