package com.envolope.oss.service;

import com.envolope.oss.dao.ReChannelDAO;
import com.envolope.oss.dao.RePackageChannelDAO;
import com.envolope.oss.dao.RePackageDAO;
import com.envolope.oss.model.ReChannel;
import com.envolope.oss.model.RePackage;
import com.envolope.oss.model.RePackageChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Summer on 2016/9/29.
 */
@Service
public class ChannelAndPackageService {

    @Autowired
    private ReChannelDAO reChannelDAO;
    @Autowired
    private RePackageDAO rePackageDAO;
    @Autowired
    private RePackageChannelDAO rePackageChannelDAO;

    /**
     * 查询渠道列表
     * @return
     */
    public List<ReChannel> selectAllChannel() {
        return reChannelDAO.selectAll();
    }

    /**
     * 查询包名列表
     * @return
     */
    public List<RePackage> selectAllPackage() {
        return rePackageDAO.selectAll();
    }

    public List<RePackageChannel> getPackageChannelList(){
        return rePackageChannelDAO.getPackageChannelList();
    }
    public List<String> getDistinctChannel(){
        return rePackageChannelDAO.getDistinctChannel();
    }
    public List<String> getDistinctPackage(){
        return rePackageChannelDAO.getDistinctPackage();
    }


}
