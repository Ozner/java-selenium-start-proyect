package helper;

public class Helper {
	public void sleepSecond(int second){
		try{
			Thread.sleep(second*1000);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
