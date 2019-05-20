package com.cheer.mybatis.Dao;

import com.cheer.mybatis.model.Emp;

public interface EmpMapper {
    Emp getEmp(Integer empno);//查询
    void insert(Emp emp);//增加
    void delete(Integer empno);//删除
    void upDate(Emp emp);//跟新
}