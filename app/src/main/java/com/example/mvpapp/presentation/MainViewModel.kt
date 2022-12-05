package com.example.mvpapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvpapp.data.ShopListRepositoryImpl // не должно быть в presentation слое
import com.example.mvpapp.domain.*

class MainViewModel : ViewModel() {

    private  val repository = ShopListRepositoryImpl

    private  val getShopListUseCase = GetShopListUseCase(repository) // пока неправильно, инъекция зависимостей правильно
    private  val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private  val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopItem>>()

    fun getShopList(){
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

    fun deleteShopListItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
        getShopList()
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
        getShopList()
    }
}