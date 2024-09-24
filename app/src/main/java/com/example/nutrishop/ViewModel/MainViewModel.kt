package com.example.nutrishop.ViewModel

import android.provider.ContactsContract.Data
import android.transition.Slide
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutrishop.Model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainViewModel():ViewModel() {

    val firebaseDatabase = FirebaseDatabase.getInstance("https://nutrishop-c15c2-default-rtdb.asia-southeast1.firebasedatabase.app")


    private val _banner = MutableLiveData<List<SliderModel>>()

     val banners: LiveData<List<SliderModel>> =_banner



    fun loadBanners(){
        val Ref = firebaseDatabase.getReference("Banner")
        Ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                 for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(SliderModel::class.java)
                    if(list!=null){
                        lists.add(list)
                    }
                 }
                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }
}