package com.giuaki.example.usecase; 

public class RequestData {
    // Từ khóa để tìm kiếm sản phẩm
    private String keyword;

    /**
     * Constructor khởi tạo đối tượng RequestData.
     * 
     * @param keyword Từ khóa tìm kiếm
     */
    public RequestData(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Phương thức lấy từ khóa tìm kiếm.
     * 
     * @return Từ khóa tìm kiếm
     */
    public String getKeyword() {
        return keyword;
    }
}
