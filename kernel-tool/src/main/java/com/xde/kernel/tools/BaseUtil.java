package com.xde.kernel.tools;


import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseUtil {
    public static final String FORMAT_YYYYMMDD = "yyyyMMdd";

    public static String newId() {
        return UUID.randomUUID().toString();
    }


    public static String getRandomByRange(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return String.valueOf(s);
    }

    public static String createProductCode() {
        String curTime = getDateTime("yyyyMMddHHmmss");
        return curTime + getRandomByRange(1000, 9999);
    }

    public static String createBaseCouponCode() {
        String curTime = getDateTime("yyMMddHHmmss");
        return curTime + getRandomByRange(100000, 999999);
    }

    public static String createProductCategoryCode() {
        String curTime = getDateTime("yyyyMMdd");
        return "C-" + curTime + getRandomByRange(1000, 9999);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.equals("") || str.trim().equals("");
    }

    /**
     * 判断一个对象是否为空
     */
    public static boolean isEmpty(Object obj) {
        return null == obj;
    }

    public static String getDateTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    public static Date getDateTime(String format, String strDateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date result = null;
        try {
            result = sdf.parse(strDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public static Date getFormatDate(String strDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date result = null;
        try {
            result = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String getDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String getDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }


    public static Date getDate(String strDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date result = null;
        try {
            result = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    public static String toString(Object objValue) {
        if (null == objValue) {
            return "";
        } else {
            return objValue.toString();
        }
    }

    public static boolean toBool(Object objValue) {
        if (null == objValue) {
            return false;
        } else if ("true".equals(objValue.toString())) {
            return true;
        } else {
            return "Y".equals(objValue.toString());
        }
    }

    public static BigDecimal toDecimal(String value) {
        BigDecimal result = null;
        if (isEmpty(value)) {
            result = new BigDecimal("0.0");
        } else {
            try {
                result = new BigDecimal(value);
            } catch (Exception e) {
                result = new BigDecimal("0.0");
            }
        }
        return result;
    }

    public static int toInt(String value) {
        int result = 0;
        try {
            result = Integer.parseInt(value);
        } catch (Exception ignored) {
        }
        return result;
    }

    public static <T> T populate(Map<String, ? extends Object> mapEntity, Class<T> entity) {
        T obj = null;
        if (!BaseUtil.isEmpty(mapEntity)) {
            try {
                obj = entity.newInstance();
                BeanUtils.populate(obj, mapEntity);
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

    /**
     * 根据生日计算年龄
     *
     * @param birthday
     * @return
     */
    public static int getAgeByBirth(String input) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_YYYYMMDD);
        int age = 0;
        try {
            Date birthday = format.parse(input);
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());
            // 当前时间
            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (!birth.after(now)) {
                // 如果传入的时间，在当前时间的后面，返回0岁
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
            }
            return age;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 字符串加密函数MD5实现
     */
    public static String md5(String password) {
        MessageDigest md;
        try {
            // 生成一个MD5加密计算摘要
            md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(password.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }


    public static void validateField(String filedValue, int dbLength, boolean isRequried, String filedName,
                                     StringBuffer message) {
        if (isRequried && BaseUtil.isEmpty(filedValue)) {
            // 必须输入
            message.append(String.format("%s必须输入！", filedName));
        }
        // 长度检查
        if (dbLength > 0 && null != filedValue && filedValue.length() > dbLength) {
            message.append(String.format("%s长度必须小于%d！", filedName, dbLength));
        }
    }

    public static void validateField(Integer filedValue, int dbLength, boolean isRequried, String filedName,
                                     StringBuffer message) {
        if (isRequried && BaseUtil.isEmpty(filedValue)) {
            // 必须输入
            message.append(String.format("%s必须输入！", filedName));
        }
        // 长度检查
        if (dbLength > 0 && null != filedValue) {
            message.append(String.format("%s长度必须小于%d！", filedName, dbLength));
        }
    }

    public static void validateField(BigDecimal filedValue, int dbLength, boolean isRequried, String filedName,
                                     StringBuffer message) {
        if (isRequried && BaseUtil.isEmpty(filedValue)) {
            // 必须输入
            message.append(String.format("%s必须输入！", filedName));
        }
        // 长度检查
        if (dbLength > 0 && null != filedValue) {
            message.append(String.format("%s长度必须小于%d！", filedName, dbLength));
        }
    }

    public static void validateField(Long filedValue, int dbLength, boolean isRequried, String filedName,
                                     StringBuffer message) {
        if (isRequried && BaseUtil.isEmpty(filedValue)) {
            // 必须输入
            message.append(String.format("%s必须输入！", filedName));
        }
        // 长度检查
        if (dbLength > 0 && null != filedValue) {
            message.append(String.format("%s长度必须小于%d！", filedName, dbLength));
        }
    }

    /**
     * 数字校验
     *
     * @param filedValue
     * @param filedName
     * @param message
     */
    public static void validateNumber(String filedValue, String filedName, StringBuffer message) {
        Pattern pattern = Pattern.compile("^[1-9]+\\d*$");
        Matcher matcher = pattern.matcher(filedValue);
        boolean isRight = matcher.matches();
        if (!isRight) {
            message.append(String.format("%s必须输入正整数！", filedName));
        }
    }

    /**
     * 金额校验
     *
     * @param filedValue
     * @param filedName
     * @param message
     */
    public static void validateMoney(String filedValue, String filedName, StringBuffer message) {
        if (null != filedValue) {
            Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
            Matcher matcher = pattern.matcher(filedValue);
            boolean isRight = matcher.matches();
            if (!isRight) {
                message.append(String.format("%s必须输入正确数字格式！", filedName));
            }
        } else {
            message.append(String.format("%s必须输入正确数字格式！", filedName));
        }

    }


    public static Object getProperty(Object obj, String fieldName) {
        String strGetMethod = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Object value = null;
        try {
            Method getMethod = obj.getClass().getDeclaredMethod(strGetMethod);
            value = getMethod.invoke(obj);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getRandom(int end) {
        Random random = new Random();
        int num = random.nextInt(end) + 1;
        return String.valueOf(num);
    }


    public static List<String> getWeekDays(int i) {
        List<String> allWeeks = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // getTimeInterval(sdf);

        Calendar calendar1 = Calendar.getInstance();
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        calendar1.setFirstDayOfWeek(Calendar.MONDAY);

        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayOfWeek) {
            calendar1.add(Calendar.DAY_OF_MONTH, -1);
        }

        // 获得当前日期是一个星期的第几天
        int day = calendar1.get(Calendar.DAY_OF_WEEK);

        // 获取当前日期前（下）x周同星几的日期
        calendar1.add(Calendar.DATE, 7 * i);

        calendar1.add(Calendar.DATE, calendar1.getFirstDayOfWeek() - day);

        String beginDate = sdf.format(calendar1.getTime());
        allWeeks.add(beginDate);
        calendar1.add(Calendar.DATE, 1);
        String data = sdf.format(calendar1.getTime());
        allWeeks.add(data);
        calendar1.add(Calendar.DATE, 1);
        data = sdf.format(calendar1.getTime());
        allWeeks.add(data);
        calendar1.add(Calendar.DATE, 1);
        data = sdf.format(calendar1.getTime());
        allWeeks.add(data);
        calendar1.add(Calendar.DATE, 1);
        data = sdf.format(calendar1.getTime());
        allWeeks.add(data);
        calendar1.add(Calendar.DATE, 1);
        data = sdf.format(calendar1.getTime());
        allWeeks.add(data);
        calendar1.add(Calendar.DATE, 1);

        String endDate = sdf.format(calendar1.getTime());
        allWeeks.add(endDate);
        System.out.print(allWeeks.toString());
        return allWeeks;
    }

    public static long getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        System.out.println(day + "天" + hour + "小时" + min + "分钟");
        return 60 * hour + min;
    }

    public static String getMinUp(String preTime, int min) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date;
        try {
            date = sdf.parse(preTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE, min + 1);
            return sdfTime.format(calendar.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public static String getMinUp(Date preTime, int min) {
        SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(preTime);
            calendar.add(Calendar.MINUTE, min + 1);
            return sdfTime.format(calendar.getTime());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public static String getDayUp(String preDate, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse(preDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, day);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public static String getMonthUp(String preDate, int month) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse(preDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, month);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public static List<String> findDates(String start, String end) {
        Calendar cal = Calendar.getInstance();
        List<Date> lDate = new ArrayList<Date>();
        List<String> dateList = new ArrayList<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dBegin = sdf.parse(start);
            Date dEnd = sdf.parse(end);

            lDate.add(dBegin);
            Calendar calBegin = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calBegin.setTime(dBegin);
            Calendar calEnd = Calendar.getInstance();
            // 使用给定的 Date 设置此 Calendar 的时间
            calEnd.setTime(dEnd);
            // 测试此日期是否在指定日期之后
            while (dEnd.after(calBegin.getTime())) {
                // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
                calBegin.add(Calendar.DAY_OF_MONTH, 1);
                lDate.add(calBegin.getTime());
            }
            for (Date date : lDate) {
                dateList.add(sdf.format(date));
            }
            return dateList;
        } catch (Exception e) {

        }
        return dateList;
    }

    public static String getWeekOfDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d;
        int w = 0;
        try {
            d = sdf.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0) {
                w = 0;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (0 == w) {
            return "6";
        } else if (1 == w) {
            return "0";
        } else if (2 == w) {
            return "1";
        } else if (3 == w) {
            return "2";
        } else if (4 == w) {
            return "3";
        } else if (5 == w) {
            return "4";
        } else if (6 == w) {
            return "5";
        } else {
            return "";
        }

    }



    public static double getRandomByRunge(double min, double max, int scl) {
        int pow = (int) Math.pow(10, scl);
        double one = Math.floor((Math.random() * (max - min) + min) * pow) / pow;
        return one;
    }

    public static String getDateDif(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
    }

    public static BigDecimal getDateDifSec(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;

        BigDecimal totalSec = new BigDecimal(sec);
        BigDecimal totalMin = new BigDecimal(min).multiply(new BigDecimal(60));
        BigDecimal totalHour = new BigDecimal(hour).multiply(new BigDecimal(3600));
        BigDecimal totalDay = new BigDecimal(day).multiply(new BigDecimal(3600 * 24));
        return totalSec.add(totalMin).add(totalHour).add(totalDay);
    }

    /**
     * 比较两个时间
     *
     * @param date1
     * @param date2
     * @return -1:date1小于date2 1:date1大于date2 0:相等
     * @throws ParseException
     */
    public static int compareDate(String date1, String date2) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sf.parse(date1);
        Date d2 = sf.parse(date2);
        if (d1.before(d2)) {
            return -1;
        } else if (d1.after(d2)) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int compareTime(String time1, String time2) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
        Date d1 = sf.parse(time1);
        Date d2 = sf.parse(time2);
        if (d1.getTime() < d2.getTime()) {
            return -1;
        } else if (d1.getTime() > d2.getTime()) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int compareDateTime(String dateTime1, String dateTime2) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = sf.parse(dateTime1);
        Date d2 = sf.parse(dateTime2);
        if (d1.getTime() < d2.getTime()) {
            return -1;
        } else if (d1.getTime() > d2.getTime()) {
            return 1;
        } else {
            return 0;
        }
    }


    /**
     * 生成订单编号:17位日期+4位随机数
     */
    public static String generateOrderSn() {
        StringBuilder sb = new StringBuilder();
        String date = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        sb.append(date);
        sb.append(BaseUtil.getRandomByRange(1000, 9999));
        return sb.toString();
    }

    /**
     * 判断时间、日期是否在指定区间
     *
     * @param startDate
     * @param endDate
     * @param startTime
     * @param endTime
     * @return fase: 无效 true：有效
     */
    public static boolean checkFixTime(String startDate, String endDate, String startTime, String endTime) {
        String nowDate = BaseUtil.getDate("yyyy-MM-dd");
        String nowTime = BaseUtil.getTime();
        try {
            if (BaseUtil.compareDate(startDate, nowDate) <= 0 && BaseUtil.compareDate(nowDate, endDate) <= 0) {
                if (BaseUtil.compareTime(startTime, nowTime) <= 0 && BaseUtil.compareTime(nowTime, endTime) <= 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static String timeStampToDate(String seconds, String format) {
        Long timeLong = Long.parseLong(seconds);
        SimpleDateFormat sdf = new SimpleDateFormat(format);// 要转换的时间格式
        Date date;
        try {
            date = sdf.parse(sdf.format(timeLong));
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String newIdWithNoLine() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String dateToCron(LocalDateTime sendTime) {
        return new StringJoiner(" ")
                .add(String.valueOf(sendTime.getSecond()))
                .add(String.valueOf(sendTime.getMinute()))
                .add(String.valueOf(sendTime.getHour()))
                .add(String.valueOf(sendTime.getDayOfMonth()))
                .add(String.valueOf(sendTime.getMonth().getValue()))
                .add("?")
                .add(String.valueOf(sendTime.getYear()))
                .toString();
    }
}
