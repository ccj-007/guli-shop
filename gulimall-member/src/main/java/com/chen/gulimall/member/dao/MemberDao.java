package com.chen.gulimall.member.dao;

import com.chen.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author chen
 * @email 596487930@qq.com
 * @date 2023-06-06 22:40:35
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
