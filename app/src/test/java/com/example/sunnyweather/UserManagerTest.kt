package com.example.sunnyweather

import com.example.sunnyweather.ut.UserManager
import com.example.sunnyweather.ut.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * UserManager 的单元测试类
 * 使用 Mockito 模拟依赖的 UserRepository
 */
class UserManagerTest {

    // 1. 模拟 UserRepository 对象（不会执行真实逻辑）
    @Mock
    private lateinit var mockUserRepository: UserRepository

    // 2. 待测试的目标类
    private lateinit var userManager: UserManager

    // 3. 每个测试方法执行前初始化
    @Before
    fun setup() {
        // 初始化 Mock 注解
        MockitoAnnotations.openMocks(this)
        // 创建待测试对象，注入模拟的依赖
        userManager = UserManager(mockUserRepository)
    }

    // 测试场景1：用户已登录（userId>0）
    @Test
    fun `isUserLoggedIn should return true when userId is valid`() {
        // 模拟 Repository 返回有效的用户ID（1001）
        `when`(mockUserRepository.getCurrentUserId()).thenReturn(1001)
        // 执行待测试方法
        val result = userManager.isUserLoggedIn()
        assertThat(result).isTrue()
    }

    // 测试场景2：用户未登录（userId=null||0）
    @Test
    fun `isUserLoggedIn should return false when userId is null`() {
        // 模拟 Repository 返回无效的用户ID（null）
        `when`(mockUserRepository.getCurrentUserId()).thenReturn(null)

        val result1 = userManager.isUserLoggedIn()
        assertThat(result1).isFalse()

        `when`(mockUserRepository.getCurrentUserId()).thenReturn(0)
        val result2 = userManager.isUserLoggedIn()
        assertThat(result2).isFalse()
    }

    // 测试场景4：已登录时获取用户完整信息
    @Test
    fun `getUserFullInfo should return full info when user is logged in`() {
        `when`(mockUserRepository.getCurrentUserId()).thenReturn(1001)
        `when`(mockUserRepository.getUserName()).thenReturn("里斯")
        `when`(mockUserRepository.getUserAge()).thenReturn(25)

        val result = userManager.getUserFullInfo()
        assertThat(result).isEqualTo("姓名：里斯，年龄：25")
    }

    // 测试场景5：未登录时获取用户完整信息
    @Test
    fun `getUserFullInfo should return not logged in when user is not logged in`() {
        `when`(mockUserRepository.getCurrentUserId()).thenReturn(null)
        val result1 = userManager.getUserFullInfo()
        assertThat(result1).isEqualTo("未登录")
        `when`(mockUserRepository.getCurrentUserId()).thenReturn(null)
        val result2 = userManager.getUserFullInfo()
        assertThat(result2).isEqualTo("未登录")
    }
}