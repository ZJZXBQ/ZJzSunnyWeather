package com.example.sunnyweather

import com.example.sunnyweather.ut.StringUtils
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class StringUtilsTest {

    @Test
    fun `isEmpty should return true when string is null or blank`() {
        assertTrue(StringUtils.isEmpty(null))
        assertTrue(StringUtils.isEmpty(""))
        assertTrue(StringUtils.isEmpty("    "))
        assertFalse(StringUtils.isEmpty("hello"))
    }

    @Test
    fun `isPhoneValid should return correct result`() {
        assertTrue(StringUtils.isPhoneValid("13320697336"))
        assertFalse(StringUtils.isPhoneValid("1332069733"))
        assertFalse(StringUtils.isPhoneValid("2332069733"))
        assertFalse(StringUtils.isPhoneValid("133206a9733"))
        assertFalse(StringUtils.isPhoneValid(""))
    }

    @Test
    fun `combineNameAndPhone should return masked string when input is valid`() {
        val result = StringUtils.combineNameAndPhone("张三", "13320697336")
        assertEquals("张三 133****7336", result)
    }

    @Test
    fun `combineNameAndPhone should throw exception when input is invalid`() {
        assertThatThrownBy {
            StringUtils.combineNameAndPhone("", "13812345678")
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessage("姓名不能为空")
        assertThatThrownBy {
            StringUtils.combineNameAndPhone("李四", "123456")
        }.isInstanceOf(IllegalArgumentException::class.java).hasMessage("手机号格式错误")
    }
}