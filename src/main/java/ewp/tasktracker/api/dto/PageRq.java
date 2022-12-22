package ewp.tasktracker.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
public class PageRq {
    private Integer pageNumber;
    private Integer pageSize;
}
