package com.example.nutrishop.ViewModel

import android.provider.ContactsContract.Data
import android.transition.Slide
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutrishop.Model.CategoriesModel
import com.example.nutrishop.Model.ItemModel
import com.example.nutrishop.Model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainViewModel():ViewModel() {

    val firebaseDatabase =
        FirebaseDatabase.getInstance("https://nutrishop-c15c2-default-rtdb.asia-southeast1.firebasedatabase.app")


    private val _banner = MutableLiveData<List<SliderModel>>()
    private val _categories = MutableLiveData<MutableList<CategoriesModel>>()
    private val _featured = MutableLiveData<MutableList<ItemModel>>()


    val categories: LiveData<MutableList<CategoriesModel>> = _categories
    val featured: LiveData<MutableList<ItemModel>> = _featured
    val banners: LiveData<List<SliderModel>> = _banner


    fun loadBanners() {
        val Ref = firebaseDatabase.getReference("Banner")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(SliderModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }

    fun loadCategory() {
        val Ref = firebaseDatabase.getReference("Category")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<CategoriesModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(CategoriesModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _categories.value = lists

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun loadFeatured() {
        val Ref = firebaseDatabase.getReference("Items")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(ItemModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                _featured.value = lists

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}