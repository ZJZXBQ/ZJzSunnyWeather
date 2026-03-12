package com.example.sunnyweather

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sunnyweather.ut.SharedPrefsHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

//如果测试的类依赖安卓 Context（如 SharedPreferences），需要使用 AndroidJUnit4 运行器。
/**
 * SharedPrefsHelper 的单元测试类
 * 使用 AndroidJUnit4 运行器，获取真实的安卓 Context
 */
@RunWith(AndroidJUnit4::class)
class SharedPrefsHelperTest {

    private lateinit var context: Context
    private lateinit var sharedPrefsHelper: SharedPrefsHelper

    @Before
    fun setup() {
        // 获取应用上下文（测试环境）
        context = ApplicationProvider.getApplicationContext()
        // 创建待测试对象
        sharedPrefsHelper = SharedPrefsHelper(context)
    }

    // 每个测试方法执行后清空数据，避免测试相互干扰
    @After
    fun teardown() {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }

    // 测试 Token 存储和读取
    @Test
    fun saveToken_and_getToken_shouldWorkCorrectly() {
        // 测试步骤1：保存Token
        val testToken = "abc123456789"
        sharedPrefsHelper.saveToken(testToken)

        val savedToken = sharedPrefsHelper.getToken()
        assertThat(savedToken).isEqualTo(testToken)

        sharedPrefsHelper.saveToken("")
        assertThat(sharedPrefsHelper.getToken()).isEmpty()
    }

    // 测试首次启动标记
    @Test
    fun setFirstLaunch_and_isFirstLaunch_should_work_correctly() {
        // 默认值：首次启动（true）
        assertThat(sharedPrefsHelper.isFirstLaunch()).isTrue()

        // 设置为非首次启动
        sharedPrefsHelper.setFirstLaunch(false)
        assertThat(sharedPrefsHelper.isFirstLaunch()).isFalse()

        // 重新设置为首次启动
        sharedPrefsHelper.setFirstLaunch(true)
        assertThat(sharedPrefsHelper.isFirstLaunch()).isTrue()
    }
}