package tech.builrun.btg.orders.dto;



import java.util.List;
import java.util.Map;

public record ApiResponse<T>(
        Map<String,Object> summary,
        List<T> data,
        PaginationResponse paginationResponse) {
}
