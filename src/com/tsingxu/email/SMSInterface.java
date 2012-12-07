package com.tsingxu.email;

public class SMSInterface
{

	public static String sendSMS(String msg, String mobile)
	{
		return SmsUtils.sendSms("http://api.dbank.com/rest.php",
				"KlmAHLmkhMYRUgyiu0QcHs6HPR52CCAO", "48669", mobile, msg);
	}

	public static void main(String[] args)
	{
		if (args.length < 2)
		{
			throw new IllegalArgumentException("Args's length less than 2");
		}
		System.out.println(sendSMS(args[0], args[1]));
	}

}
