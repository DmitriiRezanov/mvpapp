package com.example.mvpapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.mvpapp.data.ShopListRepositoryImpl // не должно быть в presentation слое
import com.example.mvpapp.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private  val repository = ShopListRepositoryImpl(application)

    private  val getShopListUseCase = GetShopListUseCase(repository) // пока неправильно, инъекция зависимостей правильно
    private  val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private  val editShopItemUseCase = EditShopItemUseCase(repository)

    private val scope = CoroutineScope(Dispatchers.IO)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem){
        scope.launch {
            deleteShopItemUseCase.deleteShopItem(shopItem)
        }

    }

    fun changeEnableState(shopItem: ShopItem){
        scope.launch {
            val newItem = shopItem.copy(enabled = !shopItem.enabled)
            editShopItemUseCase.editShopItem(newItem)
        }

    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}