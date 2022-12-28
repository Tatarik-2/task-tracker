package ewp.tasktracker.service.delivery;

import ewp.tasktracker.api.dto.delivery.CreateDeliveryRq;
import ewp.tasktracker.entity.DeliveryEntity;
import ewp.tasktracker.entity.ReleaseEntity;
import ewp.tasktracker.exception.ResourceNotFoundException;
import ewp.tasktracker.repository.DeliveryRepository;
import ewp.tasktracker.service.release.ReleaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final ReleaseService releaseService;

    @Override
    public DeliveryEntity save(CreateDeliveryRq dto) {
        ReleaseEntity releaseEntity = releaseService.findById(dto.getReleaseId());
        return deliveryRepository.save(dto.toEntity(releaseEntity));
    }

    @Override
    public DeliveryEntity findById(String id) {
        return deliveryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Delivery not found, id: " + id));
    }

    @Override
    public List<DeliveryEntity> findAll() {
        return deliveryRepository.findAll();
    }
}
