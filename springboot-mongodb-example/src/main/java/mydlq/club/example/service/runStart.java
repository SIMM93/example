package mydlq.club.example.service;

/**
 * <p>>@作者 Sc
 * <p>>@所属包 springboot-mongodb-example:mydlq.club.example.service
 * <p>>@创建时间 2021-07-29-11-28
 * <p>>@功能描述
 **/
public class runStart extends Thread {


    @Override
    public void run()
    {
        try
        {
            System.out.println("正在运行的线程名称："+ currentThread().getName()+" 开始");
            Thread.sleep(2000);    //延时2秒
            System.out.println("正在运行的线程名称："+ currentThread().getName()+" 结束");
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
