package com.sql.er.utils;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 〈一句话功能简述〉<br>
 * 〈String工具类〉
 * 
 * @author jornada
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class StringUtil {

    public static boolean isNotNull(String s) {
        if (null != s && s.trim().length() != 0)
            return true;
        return false;
    }

    public static boolean isNull(String s) {
        if (null == s || s.trim().length() == 0)
            return true;
        return false;
    }

    /**
     * 根据要求字符串长度，数字左补零
     * 
     * @author YuanBing
     * @param length
     * @return
     */
    public static String leftFillZero(String s, int length) {
        int fillLength = 0;
        if (s != null) {
            if (s.trim().length() > length) {
                return s.trim().substring(0, length);
            } else {
                fillLength = length - s.length();
            }
        } else {
            fillLength = length;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fillLength; i++) {
            sb.append("0");
        }

        if (s != null)
            return sb.append(s).toString();
        else
            return sb.toString();
    }

    /**
     * 根据要求字符串长度，字符串右补空格
     * 
     * @author YuanBing
     * @param s
     * @param length
     * @return
     */
    public static String rightFillSpace(String s, int length) {
        int fillLength = 0;
        if (s != null) {
            if (s.trim().length() > length) {
                return s.trim().substring(0, length);
            } else {
                fillLength = length - s.trim().length();
            }
        } else {
            fillLength = length;
        }
        StringBuilder sb = new StringBuilder();
        if (s != null)
            sb.append(s.trim());
        for (int i = 0; i < fillLength; i++) {
            sb.append(" ");
        }
        if (sb.length() > length)
            return sb.substring(0, length);

        return sb.toString();
    }

    /**
     * 格式化钱（如：2000L--20.00）
     * 
     * @param money
     * @return
     */
    public static String formatMoney(Long money) {
        if (money == null)
            return null;
        String m = money.toString();
        int len = m.trim().length();
        String result = "";
        switch (len) {
            case 1:
                result = "0.0" + m;
                break;
            case 2:
                result = "0." + m;
                break;
            default:
                result = m.substring(0, m.length() - 2) + "." + m.substring(m.length() - 2);
                break;
        }
        return result;
    }

    /**
     * 格式化钱（如："2000"--"20.00"）
     * 
     * @param money
     * @return
     */
    public static String formatMoney(String money) {
        if (money == null)
            return null;
        Long mon = Long.valueOf(money.trim());
        String m = mon.toString();
        int len = m.length();
        String result = "";
        switch (len) {
            case 1:
                result = "0.0" + m;
                break;
            case 2:
                result = "0." + m;
                break;
            default:
                result = m.substring(0, m.length() - 2) + "." + m.substring(m.length() - 2);
                break;
        }
        return result;
    }

    /**
     * 将string 格式的钱转换为long（如："20.00"--2000L） 输入格式必须为"20.00"
     * 
     * @param money
     * @return
     */
    public static Long moneyToLong(String money) {
        if (money == null)
            return null;
        money = money.trim();
        if (money.indexOf(".") > 0) {
            int index = money.indexOf(".");
            System.out.println(index);
            if (money.length() - index >= 3) {
                money = money.substring(0, index) + money.substring(index + 1, index + 3);
            } else if (money.length() - index == 2) {
                money = money.substring(0, index) + money.substring(index + 1) + "0";
            } else {
                money = money.substring(0, index) + "00";
            }
        } else {
            money = money + "00";
        }
        return Long.valueOf(money);
    }

    /**
     * 将string 格式的钱转换为long（如："20.0或20.00"--2000L） 输入格式必须为
     * 
     * @param money
     * @return
     */
    public static Long money2Long(String money) {
        if (money == null)
            return null;
        money = money.trim();
        if (money.indexOf(".") > 0) {
            int index = money.indexOf(".");
            System.out.println(index);
            if (money.length() - index >= 3) {
                money = money.substring(0, index) + money.substring(index + 1, index + 3);
            } else if (money.length() - index == 2) {
                money = money.substring(0, index) + money.substring(index + 1) + "0";
            } else {
                money = money.substring(0, index) + "00";
            }
        } else {
            money = money + "00";
        }
        return Long.valueOf(money);
    }

    /**
     * 将string 格式的钱转换为long（如："20.00"--2000） 输入格式必须为"20.00"
     * 
     * @param money
     * @return
     */
    public static String moneyToString(String money) {
        if (money == null)
            return null;
        return moneyToLong(money).toString();
    }

    public static String money2String(String money) {
        if (money == null)
            return null;
        return money2Long(money).toString();
    }

    /**
     * 
     * 功能描述: <br>
     * 〈数字金额大写转换〉
     * 
     * @param money 原始数字金额
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String digitUppercase(String money) {
        if (isNull(money)) {
            return money;
        }
        String digit[][] = { { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" },
                { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" } };
        String unit[] = { "", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟" };
        // 整数
        String integer = "";
        // 小数
        String decimal = "";
        if (money.contains(".")) {
            integer = money.substring(0, money.indexOf("."));
            decimal = money.substring(money.indexOf("."));
        } else {
            integer = money;
        }
        StringBuffer formatMoney = new StringBuffer();
        // 整数替换
        if (isNotNull(integer)) {
            int integerPart = Integer.parseInt(integer);
            boolean zeroFlag = false;
            for (int j = 0; j < unit.length; j++) {
                String sourceValue = digit[1][integerPart % 10] + unit[j];
                // 保留万，亿的单位
                if (j < integer.length() && ("零亿".equals(sourceValue) || "零万".equals(sourceValue))) {
                    sourceValue = sourceValue.substring(1);
                    formatMoney = formatMoney.insert(0, sourceValue);
                    continue;
                }
                // 替换零拾零佰零仟登录
                String value = sourceValue.replaceAll("^零.", "");
                if (isNotNull(value)) {
                    if (formatMoney.length() > 1) {
                        // 单位判断
                        String moneyUnit = formatMoney.substring(0, 1);
                        if ("万".equals(moneyUnit) || "亿".equals(moneyUnit)) {
                            zeroFlag = false;
                        }
                        if (zeroFlag) {
                            formatMoney.insert(0, "零");
                            zeroFlag = false;
                        }
                    }
                    formatMoney = formatMoney.insert(0, value);
                } else {
                    zeroFlag = true;
                }
                integerPart = integerPart / 10;
            }
        } else {
            formatMoney.append("零零");
        }
        // 小数替换
        if (isNotNull(decimal)) {
            for (int i = 0; i < digit[0].length; i++) {
                decimal = decimal.replace(digit[0][i], digit[1][i]);
            }
            formatMoney.append(decimal);
        }
        String formatMoneyTem = formatMoney.toString().replace("零.", "点").replace(".", "点").replaceAll("零*$", "").replaceAll("点$", "");
        //点开头添加零
        if(formatMoneyTem.startsWith("点")){
            formatMoneyTem = "零" + formatMoneyTem;
        }
        return formatMoneyTem;
    }

    /**
     * 去除字符串中的空格,换行,制表符
     * 
     * @param s
     * @return
     */
    public static String trim(String s) {
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(s);
        String after = m.replaceAll("");
        return after;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈string 转数组〉
     *
     * @param str 原始字符串
     * @param regex 分隔符
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String[] split(String str, String regex) {
        if (str == null) {
            return new String[0];
        }
        if (regex == null) {
            return new String[] {str};
        }
        List<String> result = new ArrayList<String>();
        if ("".equals(regex)) {
            for (int i = 0; i < str.length(); i++) {
                result.add(str.substring(i, i + 1));
            }
        }
        else {
            int pos = 0;
            int delPos;
            while ((delPos = str.indexOf(regex, pos)) != -1) {
                result.add(str.substring(pos, delPos));
                pos = delPos + regex.length();
            }
            if (str.length() > 0 && pos <= str.length()) {
                // Add rest of String, but not in case of empty input.
                result.add(str.substring(pos));
            }
        }
        return toStringArray(result);
    }
    
    /**
     * 
     * 功能描述: <br>
     * 〈collection转String数组〉
     *
     * @param collection
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String[] toStringArray(Collection<String> collection) {
        if (collection == null) {
            return null;
        }
        return collection.toArray(new String[collection.size()]);
    }

    public static String MD5(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 手机号校验
     *
     * @param s
     * @return
     */
    public static boolean isPhone(String s) {
        String regExp = "^((12[0-9])|(13[0-9])|(19[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(16[0-9])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(s);
        return m.matches();
    }

    /**
     * ip 校验
     *
     * @param ip
     * @return
     */
    public static boolean ipCheck(String ip) {
        String regExp = "^(\\d|[1-9]\\d|1\\d{2}|2[0-5][0-9])\\.(\\d|[1-9]\\d|1\\d{2}|2[0-5][0-9])\\.(\\d|[1-9]\\d|1\\d{2}|2[0-5][0-9])\\.(\\d|[1-9]\\d|1\\d{2}|2[0-5][0-9])$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(ip);
        return m.matches();
    }

    /**
     * url 校验
     *
     * @param url
     * @return
     */
    public static boolean urlCheck(String url) {
        if (isNull(url)) {
            return false;
        }
        String regExp = "^(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(url);
        return m.matches();
    }

}
