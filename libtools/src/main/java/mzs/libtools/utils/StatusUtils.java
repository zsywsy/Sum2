package mzs.libtools.utils;

public class StatusUtils {

	public static int STATUS_1 = 0x1;
	public static int STATUS_2 = 0x2;
	public static int STATUS_3 = 0x4;
	public static int STATUS_4 = 0x8;
	public static int STATUS_5 = 0x10;
	public static int STATUS_6 = 0x20;
	public static int STATUS_7 = 0x40;
	public static int STATUS_8 = 0x80;
	public static int STATUS_9 = 0x100;
	public static int STATUS_10 = 0x200;
	public static int STATUS_11 = 0x400;
	public static int STATUS_12 = 0x800;
	public static int STATUS_13 = 0x1000;
	public static int STATUS_14 = 0x2000;
	public static int STATUS_15 = 0x4000;
	public static int STATUS_16 = 0x8000;

	/**
	 * 测试状态是否匹配
	 * 
	 * @param status
	 *            所需比较的状态值
	 * @param input
	 *            输入的状态值
	 * @param allMatched
	 *            是否需要输入值中完全包含所需状态
	 * @return true if matches,false otherwise;
	 */
	public static boolean match(int status, int input, boolean allMatched) {
		if (allMatched) {
			return (status & input) == status;
		} else {
			return (status & input) != 0;
		}
	}

	/**
	 * @see #match(int, int, boolean)
	 */
	public static boolean match(int status, int input) {
		return match(status, input, false);
	}

	/**
	 * @see #match(int, int, boolean)
	 */
	public static boolean matchAll(int status, int input) {
		return match(status, input, true);
	}

}
