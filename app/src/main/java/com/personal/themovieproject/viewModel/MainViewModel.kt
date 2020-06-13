package com.personal.themovieproject.viewModel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.personal.themovieproject.R
import com.personal.themovieproject.model.AllCredits
import com.personal.themovieproject.model.Director
import com.personal.themovieproject.service.ApiService
import com.personal.themovieproject.service.RetrofitRequest
import com.personal.themovieproject.view.CreditListFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _director=MutableLiveData<Director>()
    val director:LiveData<Director> = _director

    private val _all_credits=MutableLiveData<AllCredits>()
    val all_credits:LiveData<AllCredits> = _all_credits

    val _isNewData =MutableLiveData<Boolean>()



    var directorHash = HashMap<String,String>()
    var directorList =ArrayList<String>()

    val retrofitRequest = RetrofitRequest()

    private val _isLoading = MutableLiveData<Int>()
    val isLoading : LiveData<Int>
        get()=_isLoading


    init {
        _isLoading.postValue(View.GONE)

        _isNewData.postValue(false)
        val stringArray = getApplication<Application>().resources.getStringArray(R.array.directorsList)
        for(item in stringArray){
            val splitresult=item.split("|")
            directorHash.putIfAbsent(splitresult[0],splitresult[1])
            directorList.add(splitresult[0])
        }
    }

    fun loadProfile(name: String){

       _isLoading.postValue(View.VISIBLE)

        val personID= directorHash[name]
        //var personID : String? =directorHash.get(key)
        val retrofit = retrofitRequest.getRetrofitObject()
        val apiService=retrofit.create(ApiService::class.java)
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
                        _isNewData.postValue(true)
                        _isLoading.postValue(View.GONE)
                        /*if (v.context is AppCompatActivity) {
                            (v.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                                .replace(R.id.fragmenthere, PersonFragment())
                                .addToBackStack(null)
                                .commit()
                        }*/
                    }
                    else{
                        Log.e("failed response",response.code().toString())
                    }
                }
            })
        }
    }


    fun getAllCredits(){
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
                if(response.code()==200) {
                    _isLoading.postValue(View.GONE)
                    _all_credits.value = response.body()!!
                    Log.d("here", _all_credits.value.toString())
                    _isNewData.postValue(true)
                    /*if (v.context is AppCompatActivity) {
                        (v.context as AppCompatActivity).supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmenthere, CreditListFragment())
                            .addToBackStack(null)
                            .commit()
                }*/
                }
                else{
                    Log.e("failed response",response.code().toString())
                }
            }
        })
    }


    fun load() = _isLoading.postValue(View.VISIBLE)

    fun stopLoad() = _isLoading.postValue(View.GONE)


}