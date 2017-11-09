package com.envolope.oss.service.userManager;

import com.envolope.oss.dao.RePackageChannelDAO;
import com.envolope.oss.model.RePackageChannel;
import com.envolope.oss.model.dto.PageDto;
import com.envolope.oss.util.JsonUtil;
import com.envolope.oss.util.el.ElBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelPackageService {

    @Autowired
    private RePackageChannelDAO rePackageChannelDAO;

    /**
     * 获取列表
     * @return
     */
    public PageDto selectByPage(int pageNo, int pageSize) {
        return rePackageChannelDAO.selectByPage(pageNo, pageSize);
    }

    /**
     * 获取渠道信息
     * @param id
     * @return
     */
    public RePackageChannel selectPackageChannel(String id) {
        String[] keys = id.split("-");
        return rePackageChannelDAO.selectByPrimaryKey(keys[0], keys[1]);
    }

    /**
     * 保存渠道信息
     * @param packageChannel
     * @return
     */
    public String save(RePackageChannel packageChannel) {

        String uploadTime = ElBase.fmtTime(System.currentTimeMillis());
        packageChannel.setUploadTime(uploadTime);

        String channelName = packageChannel.getChannelName();
        String packageName = packageChannel.getPackageName();

        RePackageChannel rePackageChannel = rePackageChannelDAO.selectByPrimaryKey(channelName, packageName);
        if (rePackageChannel == null) { // 无记录,则为添加
            // 查询当前最大app_id的记录
            RePackageChannel max = rePackageChannelDAO.selectMaxAppId();
            packageChannel.setAppId(max.getAppId() + 1);
            rePackageChannelDAO.insertSelective(packageChannel);
        }else { // 有记录,则为修改
            rePackageChannelDAO.updateByPrimaryKeySelective(packageChannel);
        }
        return JsonUtil.buildSuccess();
    }

    /**
     * 保存渠道包
     *
     * @param id
     * @param apkUrl
     * @return
     */
    public String updateApk(String id, String apkUrl) {
        String[] keys = id.split("-");
        RePackageChannel channel = new RePackageChannel();
        channel.setChannelName(keys[0]);
        channel.setPackageName(keys[1]);
        channel.setApkUrl(apkUrl);
        rePackageChannelDAO.updateByPrimaryKeySelective(channel);
        return JsonUtil.buildSuccess("上传成功");
    }



    /**
     * 删除渠道信息
     * @param ids
     * @return
     */
    public boolean delete(List<String> ids) {
        for (String id : ids) {
            String[] keys = id.split("-");
            RePackageChannel rePackageChannel = rePackageChannelDAO.selectByPrimaryKey(keys[0], keys[1]);
            if (rePackageChannel != null) {
                rePackageChannelDAO.deleteByPrimaryKey(keys[0], keys[1]);
            }
        }
        return true;
    }

    /**
     * 获取列表
     * @return
     */
    public List<RePackageChannel> getPackageChannelList(){
        return rePackageChannelDAO.getPackageChannelList();
    }

}
