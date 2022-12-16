package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateDeliveryRq;
import ewp.tasktracker.entity.DeliveryEntity;

import java.util.List;

/**
 * Сервис для работы с поставками
 */
public interface DeliveryService {

    DeliveryEntity save(CreateDeliveryRq dto);

    DeliveryEntity findById(String id);

    List<DeliveryEntity> findAll();
}
