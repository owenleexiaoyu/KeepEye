package com.owenandroid.keepeye.zhihu;

import com.owenandroid.keepeye.model.ZhihuDailyData;
import com.owenandroid.keepeye.utils.BasePresenter;
import com.owenandroid.keepeye.utils.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2017-11-22.
 * 使用契约类来统一管理view与presenter的所有的接口
 * 这种方式使得view与presenter中有哪些功能，一目了然，维护起来也很方便。
 */

public interface ZhihuDailyContract {
    /**
     * Presenter 接口定义了该界面（功能）中所有的用户操作事件
     */
    interface Presenter extends BasePresenter{

        //加载数据
        void loadData();
    }

    /**
     * View 接口定义了该界面（功能）中所有的UI状态情况
     */
    interface View extends BaseView<Presenter> {
        void showLoading();
        void hideLoading();
        //展示所有的消息条目
        void showStoties(List<ZhihuDailyData> dataList);
    }
}
