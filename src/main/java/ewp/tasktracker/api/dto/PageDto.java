package ewp.tasktracker.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageDto<T> {
    private List<T> items;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer total;
}
