import java.util.List;

public class Pagination<T> {
    private List<T> items;
    private int pageSize = 5;
    private int currentPage = 1;

    public Pagination(List<T> items) {
        this.items = items;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        if (items.isEmpty()) return 1;
        return (int) Math.ceil((double) items.size() / pageSize);
    }

    public void nextPage() {
        if (currentPage < getTotalPages()) currentPage++;
    }

    public void prevPage() {
        if (currentPage > 1) currentPage--;
    }

    public void goToPage(int page) {
        if (page >= 1 && page <= getTotalPages()) {
            currentPage = page;
        }
    }

    public void setPageSize(int size) {
        if (size > 0) {
            pageSize = size;
            currentPage = 1;
        }
    }

    public List<T> getCurrentItems() {
        int start = (currentPage - 1) * pageSize;
        int end = Math.min(currentPage * pageSize, items.size());
        return items.subList(start, end);
    }

    public String getStatus() {
        int start = (currentPage - 1) * pageSize;
        int end = Math.min(currentPage * pageSize, items.size());
        return "\nPage " + currentPage + " of " + getTotalPages() +
               " | Showing " + (end - start) + " of " + items.size() + " items";
    }
}
