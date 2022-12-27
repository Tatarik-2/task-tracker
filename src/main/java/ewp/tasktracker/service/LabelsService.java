package ewp.tasktracker.service;

import ewp.tasktracker.api.dto.CreateDeliveryRq;
import ewp.tasktracker.api.dto.CreateLabelRq;
import ewp.tasktracker.api.dto.LabelsDto;
import ewp.tasktracker.entity.LabelsEntity;

import java.util.List;

public interface LabelsService {
    LabelsDto save(CreateLabelRq dto);

    LabelsDto findById(String id);
    List<LabelsDto> findAll();
}