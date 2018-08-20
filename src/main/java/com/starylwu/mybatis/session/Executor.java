package com.starylwu.mybatis.session;

/**
 * @Auther: Wuyulong
 * @Date: 2018/8/18 08:40
 * @Description:
 */
public interface Executor {

    <E> E query(String sql, Object parameter);
}
