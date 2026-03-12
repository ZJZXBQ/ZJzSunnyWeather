package com.example.sunnyweather.ut

object StringUtils {

    /**
     * 判断字符串是否为空（包括空字符串和全空格）
     * @param str 待判断的字符串
     * @return true=空，false=非空
     */
    fun isEmpty(string: String?): Boolean {
        //trim() 是Kotlin/String的扩展函数，用于移除字符串首尾的空白字符
        return string == null || string.trim().isEmpty()
    }

    /**
     * 验证手机号格式（简单版：11位数字，以1开头）
     * @param phone 待验证的手机号
     * @return true=格式正确，false=格式错误
     */
    fun isPhoneValid(phone: String): Boolean {
        if (isEmpty(phone)) {
            return false
        }
        //正则表达式，用于验证手机号格式：
        //^ - 匹配字符串开头
        //1 - 第一位必须是数字1
        //[3-9] - 第二位必须是3-9之间的数字
        //\\d{9} - 后面跟着9位数字（\d表示数字，{9}表示重复9次）
        //$ - 匹配字符串结尾
        val phoneRegex = "^1[3-9]\\d{9}$".toRegex()
        return phoneRegex.matches(phone)
    }

    /**
     * 拼接姓名和手机号（脱敏显示）
     * 示例：张三 + 13812345678 → 张三 138****5678
     * @param name 姓名
     * @param phone 手机号
     * @return 拼接后的脱敏字符串
     */
    fun combineNameAndPhone(name: String, phone: String): String {
        if (isEmpty(name)) {
            throw IllegalArgumentException("姓名不能为空")
        }
        if (!isPhoneValid(phone)) {
            throw IllegalArgumentException("手机号格式错误")
        }
        // 手机号脱敏：保留前3位和后4位，中间用****代替
        val maskedPhone = phone.replaceRange(3, 7, "****")
        return "$name $maskedPhone"
    }
}