package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateDeliveryRq;
import ewp.tasktracker.api.dto.CreateLabelRq;
import ewp.tasktracker.entity.LabelsEntity;

import java.util.List;

public interface LabelsService {
    LabelsEntity save(CreateLabelRq dto);

    LabelsEntity findById(String id);
    List<LabelsEntity> findAll();
}
