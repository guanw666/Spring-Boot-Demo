package com.example.demo.Service;

import com.example.demo.dto.PaginationDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.exception.CustomizeException;
import com.example.demo.mapper.QuestionExtMapper;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionExample;
import com.example.demo.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private QuestionExtMapper questionExtMapper;

    @Resource
    private UserMapper userMapper;

    public PaginationDTO list(Integer pageNum, Integer pageSize) {
        Integer offset = (pageNum - 1) * pageSize;
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, pageSize));
        User user;
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            user = userMapper.selectByPrimaryKey(question.getCreator());
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        Integer totalPageNum = Math.toIntExact(questionMapper.countByExample(new QuestionExample()));
        // 组装分页对象
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setDatas(questionDTOList);
        paginationDTO.setPaginationDTO(totalPageNum, pageNum, pageSize);


        return paginationDTO;
    }

    public PaginationDTO list(Long userId, Integer pageNum, Integer pageSize) {
        Integer offset = (pageNum - 1) * pageSize;
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, pageSize));

        User user;
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            user = userMapper.selectByPrimaryKey(question.getCreator());
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        example.clear();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalPageNum = Math.toIntExact(questionMapper.countByExample(example));

        // 组装分页对象
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setDatas(questionDTOList);
        paginationDTO.setPaginationDTO(totalPageNum, pageNum, pageSize);


        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void saveOrUpdate(Question question) {
        if (question.getId() == null) {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insertSelective(question);
        } else {
            question.setGmtModified(System.currentTimeMillis());
            int updated = questionMapper.updateByPrimaryKeySelective(question);
            if (updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_UPDATE_FAIL);
            }
        }
    }

    /**
     * 查看问题后，查看数自增（并发相对安全）
     */
    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1L);
        questionExtMapper.incView(question);
    }
}
