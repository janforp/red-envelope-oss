package com.envolope.oss.dao;

import com.envolope.oss.model.RePackageChannel;
import com.envolope.oss.model.dto.PageDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by com.envolope.oss.MybatisCodeGenerate on 2016-09-30
 */
@Repository
public class RePackageChannelDAO extends BaseSqlSessionDaoSupport {
    public int deleteByPrimaryKey(String channelName, String packageName) {
        RePackageChannel _key = new RePackageChannel();
        _key.setChannelName(channelName);
        _key.setPackageName(packageName);
        return getSqlSession().delete("re_package_channel.deleteByPrimaryKey", _key);
    }

    public void insert(RePackageChannel record) {
        getSqlSession().insert("re_package_channel.insert", record);
    }

    public void insertSelective(RePackageChannel record) {
        getSqlSession().insert("re_package_channel.insertSelective", record);
    }

    public void insertBatch(List<RePackageChannel> records) {
        if (records == null || records.isEmpty()) {
            return;
        }
        getSqlSession().insert("re_package_channel.insertBatch", records);
    }

    public RePackageChannel selectByPrimaryKey(String channelName, String packageName) {
        RePackageChannel _key = new RePackageChannel();
        _key.setChannelName(channelName);
        _key.setPackageName(packageName);
        return getSqlSession().selectOne("re_package_channel.selectByPrimaryKey", _key);
    }

    public int updateByPrimaryKeySelective(RePackageChannel record) {
        return getSqlSession().update("re_package_channel.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(RePackageChannel record) {
        return getSqlSession().update("re_package_channel.updateByPrimaryKey", record);
    }

    /**
     * 获取所有数据列表
     * @return
     */
    public List<RePackageChannel> getAllList(){
        return getSqlSession().selectList("re_package_channel.getAllList");
    }

    /**
     * 返回的pageDto.list是List<RePackageChannel>
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageDto selectByPage(int pageNo, int pageSize) {
        PageDto page = new PageDto();
        Long rowCount = getSqlSession().selectOne("re_package_channel.selectCount");
        if (rowCount == null) {
            rowCount = 0L;
        }
        page.init(pageNo, pageSize, rowCount);
        pageNo = page.getPageNo();// 有可能pageNo超过了允许的范围，所以使用PageDto计算过的结果
        List<RePackageChannel> list;
        if (rowCount <= 0) {
            list = new ArrayList<>(0);
        } else {
            Map<String, Object> param = new HashMap<>(2);
            param.put("start", (pageNo - 1) * pageSize);
            param.put("pageSize", pageSize);
            list = getSqlSession().selectList("re_package_channel.selectByPage", param);
        }
        page.setList(list);
        return page;
    }

    /**
     * 查询当前最大app_id的记录
     * @return
     */
    public RePackageChannel selectMaxAppId() {
        return getSqlSession().selectOne("re_package_channel.selectMaxAppId");
    }


    /**
     * 获取列表
     * @return
     */
    public List<RePackageChannel> getPackageChannelList(){
        return getSqlSession().selectList("re_package_channel.getPackageChannelList");
    }

    public List<String> getDistinctChannel(){
        return getSqlSession().selectList("re_package_channel.getDistinctChannel");
    }
    public List<String> getDistinctPackage(){
        return getSqlSession().selectList("re_package_channel.getDistinctPackage");
    }
}