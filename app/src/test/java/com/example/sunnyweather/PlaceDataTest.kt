package com.example.sunnyweather

import com.example.sunnyweather.logic.model.Location
import com.example.sunnyweather.logic.model.Place
import com.example.sunnyweather.logic.model.PlaceData
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PlaceDataTest {
    @Test
    fun `Location 初始化后字段值正确`() {
        val lat = "30.67"
        val lng = "104.06"

        val location = Location(lat, lng)

        //验证字段值是否正确
        assertEquals(lat, location.lat)
        assertEquals(lng, location.lng)
    }

    @Test
    fun `Place 初始化和序列化反序列化正确`() {
        // 1. 准备测试数据
        val name = "成都市"
        val location = Location("30.67", "104.06")
        val address = "四川省成都市"

        val place = Place(name, location, address)

        assertEquals(name, place.name)
        assertEquals(location, place.location)
        assertEquals(address, place.address)

        val gson = Gson()
        val placeJson = gson.toJson(place)
        assertTrue(placeJson.contains("\"formatted_address\":\"$address\""))
        assertFalse(placeJson.contains("\"address\":\"$address\""))

        // 反序列化：从 JSON 转对象，验证字段映射正确
        val jsonStr = """
            {
                "name":"${name}",
                "location":{"lat":"${location.lat}","lng":"${location.lng}"},
                "formatted_address":"${address}"
            }
        """.trimIndent()
        val parsedPlace = gson.fromJson(jsonStr, Place::class.java)
        assertEquals(name, parsedPlace.name)
        assertEquals(address, parsedPlace.address) // 反序列化后 address 字段正确
    }

    // 测试 PlaceData 数据类的整体行为
    @Test
    fun `PlaceData 初始化和字段验证正确`() {
        // 1. 准备测试数据
        val status = "ok"
        val query = "成都"
        val placeList = listOf(
            Place("成都市", Location("30.67", "104.06"), "四川省成都市"),
            Place("成华区", Location("30.68", "104.08"), "四川省成都市成华区")
        )

        // 2. 创建 PlaceData 实例
        val placeData = PlaceData(status, query, placeList)

        // 3. 验证顶层字段
        assertEquals(status, placeData.status)
        assertEquals(query, placeData.query)

        // 4. 验证列表字段
        assertEquals(2, placeData.placeList.size)
        assertEquals("成都市", placeData.placeList[0].name)
        assertEquals("成华区", placeData.placeList[1].name)

        // 进阶：验证列表元素的嵌套字段
        assertEquals("30.67", placeData.placeList[0].location.lat)
        assertEquals("四川省成都市成华区", placeData.placeList[1].address)
    }

    @Test
    fun `处理空值场景正确`(){
        val emptyPlaceData= PlaceData("error","",emptyList())
        assertEquals(0,emptyPlaceData.placeList.size)
        assertEquals("",emptyPlaceData.query)
        // 测试空字符串字段
        val emptyLocation = Location("", "")
        assertEquals("", emptyLocation.lat)
    }

}