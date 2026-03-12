package com.example.sunnyweather.ut

/**
 * 用户信息业务类（依赖 UserRepository）
 * 负责用户登录状态判断、用户信息拼接等业务逻辑
 */
class UserManager(private val userRepository: UserRepository) {

    /**
     * 判断用户是否已登录
     * 逻辑：Repository 返回的用户ID非空且大于0，则视为已登录
     * @return true=已登录，false=未登录
     */
    fun isUserLoggedIn(): Boolean {
        val userId = userRepository.getCurrentUserId()
        return userId != null && userId > 0
    }

    /**
     * 获取用户完整信息（姓名+年龄）
     * @return 拼接后的用户信息，未登录则返回"未登录"
     */
    fun getUserFullInfo(): String {
        if (!isUserLoggedIn()) {
            return "未登录"
        }
        val userName = userRepository.getUserName()
        val userAge = userRepository.getUserAge()
        return "姓名：$userName，年龄：$userAge"
    }
}

/**
 * 用户数据仓库接口（模拟依赖）
 * 实际项目中可能对接网络/本地数据库
 */
interface UserRepository {
    // 获取当前用户ID
    fun getCurrentUserId(): Int?

    // 获取用户名
    fun getUserName(): String

    // 获取用户年龄
    fun getUserAge(): Int
}