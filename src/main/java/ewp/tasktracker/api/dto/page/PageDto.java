package ewp.tasktracker.api.dto.page;

import ewp.tasktracker.api.dto.history.HistoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDto<T> {
    private List<T> items;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer total;

    public static <T> PageDto<T> getEmptyPageDto(){
        PageDto<T> pageDto = new PageDto<>();
        pageDto.setTotal(0);
        return pageDto;
    }

}
