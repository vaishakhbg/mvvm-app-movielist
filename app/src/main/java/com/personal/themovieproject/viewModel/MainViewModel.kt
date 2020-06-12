package com.personal.themovieproject.viewModel

import android.transition.Visibility
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.personal.themovieproject.R
import com.personal.themovieproject.model.AllCredits
import com.personal.themovieproject.model.Director
import com.personal.themovieproject.service.ApiService
import com.personal.themovieproject.service.RetrofitRequest
import com.personal.themovieproject.view.CreditListFragment
import com.personal.themovieproject.view.PersonFragment
import kotlinx.android.synthetic.main.selectdirector.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel:ViewModel() {
    private val _director=MutableLiveData<Director>()
    val director:LiveData<Director> = _director

    private val _all_credits=MutableLiveData<AllCredits>()
    val all_credits:LiveData<AllCredits> = _all_credits

    val directorHash : HashMap<String,String> = hashMapOf<String,String>("dv" to "137427","dc" to "136495")
    val retrofitRequest = RetrofitRequest()

    private val _isLoading = MutableLiveData<Int>()
    val isLoading : LiveData<Int>
        get()=_isLoading


    init {
        _isLoading.postValue(View.GONE)
    }

    fun loadProfile(v: View){

        _isLoading.postValue(View.VISIBLE)

        val key=getKey(v.id)
        var personID : String? =directorHash.get(key)
        var retrofit = retrofitRequest.getRetrofitObject()
        var apiService=retrofit.create(ApiService::class.java)
        if(!(personID.isNullOrEmpty())){
            val call=apiService.getPerson(personID,retrofitRequest.tokenID)
            Log.d("request",call.request().toString())
            call.enqueue(object : Callback<Director>{
                override fun onFailure(call: Call<Director>, t: Throwable) {
                    Log.d("here","fail");
                    _isLoading.postValue(View.GONE)
                }

                override fun onResponse(call: Call<Director>, response: Response<Director>) {
                    if(response.code()==200){
                        _director.value=response.body()!!
                        Log.d("here",response.body().toString())
                        _isLoading.postValue(View.GONE)
                        if (v.context is AppCompatActivity) {
                            (v.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                                .replace(R.id.fragmenthere, PersonFragment())
                                .addToBackStack(null)
                                .commit()
                        }
                    }
                    else{
                        Log.e("failed response",response.code().toString())
                    }
                }
            })
        }
    }


    fun getAllCredits(v:View){
        _isLoading.postValue(View.VISIBLE)
        var retrofit = retrofitRequest.getRetrofitObject()
        var apiService=retrofit.create(ApiService::class.java)
        val call=apiService.getCredits(_director.value!!.id,retrofitRequest.tokenID)
        Log.d("request",call.request().toString())
        call.enqueue(object : Callback<AllCredits>{
            override fun onFailure(call: Call<AllCredits>, t: Throwable) {
                Log.d("here","fail");
                _isLoading.postValue(View.GONE)
            }

            override fun onResponse(call: Call<AllCredits>, response: Response<AllCredits>) {
                if(response.code()==200){
                    _isLoading.postValue(View.GONE)
                    _all_credits.value=response.body()!!
                    Log.d("here",_all_credits.value.toString())
                    if (v.context is AppCompatActivity) {
                        (v.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmenthere, CreditListFragment())
                            .addToBackStack(null)
                            .commit()
                }
                else{
                    Log.e("failed response",response.code().toString())
                }
            }
        }
    })
    }

    fun getKey(id:Int):String?{
        when(id){
            R.id.dv -> return "dv"
            R.id.dc -> return "dc"
            else -> {
                return null
            }
        }
    }
}