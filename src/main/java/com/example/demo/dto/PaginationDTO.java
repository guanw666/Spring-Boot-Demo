package com.example.demo.dto;

import com.example.demo.model.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> datas;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showLastPage;
    private Integer pageNum;
    private Integer pageSize;
    /**
     * 总数
     */
    private Integer totalNum;
    /**
     * 总页数
     */
    private Integer totalPageNum;
    private List<Integer> pages;

    public void setPaginationDTO(Integer totalNum, Integer pageNum, Integer pageSize) {
        this.totalNum = totalNum;
        if (totalNum == 0) {
            showPrevious = false;
            showFirstPage = false;
            showNext = false;
            showLastPage = false;
            return;
        }
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalPageNum = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        this.pages = new ArrayList<>(7);
        for (int i = 1; i <= totalPageNum; i++) {
            if (Math.abs(pageNum - i) <= 3) {
                pages.add(i);
            }
        }
        showFirstPage = !pages.contains(1);
        showLastPage = !pages.contains(totalPageNum);
        showPrevious = !(pageNum == 1);
        showNext = !pageNum.equals(totalPageNum);
    }

    public static void main(String[] args) {
        new PaginationDTO().setPaginationDTO(15, 5, 3);
    }
}
