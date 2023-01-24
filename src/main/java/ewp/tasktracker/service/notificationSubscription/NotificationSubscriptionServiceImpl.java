package ewp.tasktracker.service.notificationSubscription;

import ewp.tasktracker.api.dto.notificationSubscription.NotificationSubscriptionDto;
import ewp.tasktracker.api.util.PageUtil;
import ewp.tasktracker.repository.NotificationSubscriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class NotificationSubscriptionServiceImpl implements NotificationSubscriptionService {
    private final NotificationSubscriptionRepository notificationSubscriptionRepository;
    private final PageUtil pageUtil;

    @Override
    public List<NotificationSubscriptionDto> findAll(Integer pageSize, Integer pageNumber) {
        pageSize = pageUtil.pageSizeControl(pageSize);
        return notificationSubscriptionRepository.findAll(PageRequest.of(pageNumber, pageSize)).stream().
                map(NotificationSubscriptionDto::new).collect(Collectors.toList());
    }
}
