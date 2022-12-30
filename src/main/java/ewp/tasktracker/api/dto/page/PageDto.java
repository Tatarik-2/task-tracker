package ewp.tasktracker.api.dto.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto<T> {
    private List<T> items;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer total;
}
