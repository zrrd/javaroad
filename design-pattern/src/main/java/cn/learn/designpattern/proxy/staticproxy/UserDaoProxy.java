package cn.learn.designpattern.proxy.staticproxy;

import cn.learn.designpattern.model.User;
import cn.learn.designpattern.proxy.dao.UserDao;
import cn.learn.designpattern.proxy.dao.UserDaoImpl;

/**
 * UserDao代理类
 *
 * @author shaoyijiong
 * @date 2018/7/12
 */
public class UserDaoProxy implements UserDao {

  /**
   * 需要的代理类
   */
  private UserDao target;

  UserDaoProxy(UserDao target) {
    this.target = target;
  }

  /**
   * 代理方法
   *
   * @param user 要保存的用户
   */
  @Override
  public void save(User user) {
    System.out.println("代理开始了");
    System.out.println("代理做事");
    target.save(user);
    System.out.println("代理开始了");
    System.out.println("代理作数");
  }

  @Override
  public void growUp(User user) {
    System.out.println("代理开始了");
    System.out.println("代理做事");
    target.growUp(user);
    System.out.println("代理开始了");
    System.out.println("代理作数");
  }
}


/**
 * 测试类
 */
class Test {

  public static void main(String[] args) {
    UserDao userDaoImpl = new UserDaoImpl();
    //将代理类与要被代理的类做关联
    UserDaoProxy userDaoProxy = new UserDaoProxy(userDaoImpl);

    //执行代理方法
    User u = new User("a", 10);
    userDaoProxy.save(u);
  }
}