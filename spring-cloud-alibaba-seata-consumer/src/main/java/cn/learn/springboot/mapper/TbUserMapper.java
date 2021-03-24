package cn.learn.springboot.mapper;


import cn.learn.springboot.domain.TbUser;

public interface TbUserMapper {

  int deleteByPrimaryKey(Integer id);

  int insert(TbUser record);

  int insertSelective(TbUser record);

  TbUser selectByPrimaryKey(Integer id);

  int updateByPrimaryKeySelective(TbUser record);

  int updateByPrimaryKey(TbUser record);
}