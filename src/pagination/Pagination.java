package pagination;

import java.util.Scanner;

public interface Pagination {
    int setNewRow();
    void setPageSize(Scanner scanner);
    int savePageSize(int pageSize);
}
