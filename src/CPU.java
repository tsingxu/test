class CPU
{

	public static void main(String[] args)
	{
		long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024);
		System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024);
		long maxMemory = Runtime.getRuntime().maxMemory(); // 系统总内存
		System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024);

		double usedMemroyRate = (usedMemory * 1.0d) / maxMemory;

		System.out.println(usedMemroyRate);
	}
}