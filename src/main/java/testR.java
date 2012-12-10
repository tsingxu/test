import java.util.concurrent.TimeUnit;

public class testR
{

	public static void main(String[] args) throws InterruptedException
	{
		System.out.println(System.currentTimeMillis());

		TimeUnit.SECONDS.sleep(0);

		System.out.println(System.currentTimeMillis());
	}
}
