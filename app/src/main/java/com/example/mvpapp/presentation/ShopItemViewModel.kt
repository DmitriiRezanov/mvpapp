package com.example.mvpapp.presentation

import androidx.lifecycle.ViewModel
import com.example.mvpapp.data.ShopListRepositoryImpl
import com.example.mvpapp.domain.AddShopItemUseCase
import com.example.mvpapp.domain.EditShopItemUseCase
import com.example.mvpapp.domain.GetShopItemUseCase
import com.example.mvpapp.domain.ShopItem

class ShopItemViewModel : ViewModel(){

    private  val repository = ShopListRepositoryImpl

    private val getShopItemUsesCase = GetShopItemUseCase(repository)
    private val addShopItemUsesCase = AddShopItemUseCase(repository)
    private val editShopItemUsesCase = EditShopItemUseCase(repository)

    fun getShopItem(shopItemId : Int) {
        val item = getShopItemUsesCase.getShopItem(shopItemId)
    }

    fun addShopItem (inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name,count)
        if (fieldsValid) {
            val shopItem = ShopItem(name,count, true)
            addShopItemUsesCase.addShopItem(shopItem)
        }
    }

    fun editShopItem (inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name,count)
        if (fieldsValid) {
            val shopItem = ShopItem(name,count, true)
            editShopItemUsesCase.editShopItem(shopItem)
        }
    }

    private fun parseName(inputName : String?) : String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int{
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e : Exception){
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()){
            // TODO: show error input name
            result = false
        }
        if (count <= 0){
            // TODO: show error input count
            result = false
        }
        return result
    }
}