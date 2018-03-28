package com.nlf.extend.dao.sql.dbType.oracle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.nlf.Bean;
import com.nlf.dao.exception.DaoException;
import com.nlf.dao.paging.PageData;
import com.nlf.extend.dao.sql.SqlConnection;
import com.nlf.extend.dao.sql.dbType.common.ASqlSelecter;
import com.nlf.log.Logger;

/**
 * SQL查询器的oracle实现
 * @author 6tail
 *
 */
public class OracleSelecter extends ASqlSelecter{
  public boolean support(String dbType){
    return "oracle".equalsIgnoreCase(dbType);
  }

  public List<Bean> top(int count){
    params.clear();
    sql = buildSql();
    sql = "SELECT * FROM ("+sql+") WHERE ROWNUM <= "+count;
    Logger.getLog().debug(buildLog());
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try{
      stmt = ((SqlConnection)connection).getConnection().prepareStatement(sql);
      bindParams(stmt);
      rs = stmt.executeQuery();
      return toBeans(rs);
    }catch(SQLException e){
      throw new DaoException(e);
    }finally{
      finalize(stmt,rs);
    }
  }
  
  public PageData page(int pageNumber,int pageSize){
    PageData d = new PageData();
    d.setPageSize(pageSize);
    d.setPageNumber(pageNumber);
    d.setRecordCount(count());
    if(d.getPageNumber()>d.getPageCount()){
      return d;
    }
    params.clear();
    sql = buildSql();
    sql = "SELECT * FROM (SELECT NLFTABLE_.*,ROWNUM RN_ FROM ("+sql+") NLFTABLE_ WHERE ROWNUM <= "+(d.getPageNumber()*d.getPageSize())+") WHERE RN_ > "+((d.getPageNumber()-1)*d.getPageSize());
    Logger.getLog().debug(buildLog());
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Bean> l = null;
    try{
      stmt = ((SqlConnection)connection).getConnection().prepareStatement(sql);
      bindParams(stmt);
      rs = stmt.executeQuery();
      l = toBeans(rs);
    }catch(SQLException e){
      throw new DaoException(e);
    }finally{
      finalize(stmt,rs);
    }
    d.setData(l);
    return d;
  }
}