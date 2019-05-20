import com.cheer.mybatis.Dao.EmpMapper;
import com.cheer.mybatis.model.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

public class EmpMapperTest {
    private static final Logger LOGGER = LogManager.getLogger(EmpMapperTest.class);

    @Test
    public void getEmp() {
        InputStream in = null;
        try {
            // 导入mybatis的配置文件 mybatis-config.xml
            in = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建一个SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 打开一个会话
        SqlSession session = factory.openSession();
        // 获取EmpMapper对象
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        // 查询emp
        Emp emp = mapper.getEmp(7788);
        System.out.println(emp);
        LOGGER.info(emp);

        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void insert() {
        InputStream in = null;
        try {
            // 导入mybatis的配置文件 mybatis-config.xml
            in = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建一个SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        // 打开一个会话
        SqlSession session = factory.openSession();
        // 获取EmpMapper对象
        EmpMapper mapper = session.getMapper(EmpMapper.class);
        // 增加
        Emp emp = new Emp();
        emp.setEmpno(9921);
        emp.setEname("刘翔");
        emp.setJob("工程师");
        emp.setDeptno(20);
        mapper.insert(emp);
        //增删改需要提交
        session.commit();

      LOGGER.info(emp);

        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete(){
        InputStream is=null;
        SqlSession sqlSession=null;
        try {
            //导入mybatis配置文件，将他读写成字节流
          is = Resources.getResourceAsStream("mybatis-config.xml");

          //创建一个sqlsessionFactory对象
           SqlSessionFactory sqlSessionFactory= new SqlSessionFactoryBuilder().build(is);
           //打开一个会话
           sqlSession = sqlSessionFactory.openSession();
            // 获取EmpMapper对象
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            //操作
            mapper.delete(8888);
            //提交
            sqlSession.commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    @Test
        public void upDate(){
        //使用字节流读取mybatis配置文件信息
        InputStream is = null;
        SqlSession sqlSession1=null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
            //创建sqlsessionFactory工厂对象
            SqlSessionFactory sqlSession = new SqlSessionFactoryBuilder().build(is);
            //打开一个会话
             sqlSession1 = sqlSession.openSession();
            //获取EmpMapper对象
            EmpMapper empMapper = sqlSession1.getMapper(EmpMapper.class);
            Emp emp = new Emp();
            emp.setEmpno(9921);
            emp.setJob("运动员");
            emp.setCom(222.00);
            emp.setMgr(3343);
            emp.setDeptno(30);
            empMapper.upDate(emp);
            sqlSession1.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            if(sqlSession1!=null){
                sqlSession1.close();
            }
        }
    }
}