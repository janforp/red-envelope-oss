package com.envolope.oss.service.integralWall;

import com.envolope.oss.dao.ReTaskLabelDAO;
import com.envolope.oss.model.ReTaskLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jan on 16/10/18.
 * 标签
 */
@Service
public class LabelService {

    @Autowired
    private ReTaskLabelDAO reTaskLabelDAO;

    public List<ReTaskLabel> getAll(){
        return reTaskLabelDAO.getAll();
    }
}
