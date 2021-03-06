package cn.learn.designpattern.chain;

/**
 * 版权处理
 *
 * @author 邵益炯
 * @date 2018/10/29
 */
public class CopyrightProcess implements Process {

  @Override
  public void doProcess(String msg) {
    System.out.println(msg + "版权处理");
  }
}
