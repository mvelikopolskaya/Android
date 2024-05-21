package com.example.myapplication


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                getUserInfo()
            }
        }

        binding.rebootBtn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                    getUserInfo()
            }
        }
    }

    private suspend fun getUserInfo(){
        val response = RetrofitServices.searchUserApi.getUser()
        val items = response.body()?.results
        if(response.isSuccessful){
            if (items != null) {
                for (i in 0 until items.count()) {
                    val stringBuilder : StringBuilder = StringBuilder().append("Gender: ").append(items[i].gender).append("\n")
                        .append("Title: ").append(items[i].name.title).append("\n")
                        .append("First name: ").append(items[i].name.first).append("\n")
                        .append("Last name: ").append(items[i].name.last).append("\n")
                        .append("Street number: ").append(items[i].location.street.number).append("\n")
                        .append("Street name: ").append(items[i].location.street.name).append("\n")
                        .append("City: ").append(items[i].location.city).append("\n")
                        .append("State: ").append(items[i].location.state).append("\n")
                        .append("Country: ").append(items[i].location.country).append("\n")
                        .append("Postcode: ").append(items[i].location.postcode).append("\n")
                        .append("Latitude: ").append(items[i].location.coordinate.latitude).append("\n")
                        .append("Longitude: ").append(items[i].location.coordinate.longitude).append("\n")
                        .append("Timezone offset: ").append(items[i].location.timezone.offset).append("\n")
                        .append("Timezone description: ").append(items[i].location.timezone.description).append("\n")
                        .append("E-mail: ").append(items[i].email).append("\n")
                        .append("UUID: ").append(items[i].login.uuid).append("\n")
                        .append("Username: ").append(items[i].login.username).append("\n")
                        .append("Password: ").append(items[i].login.password).append("\n")
                        .append("Salt: ").append(items[i].login.salt).append("\n")
                        .append("MD5: ").append(items[i].login.md5).append("\n")
                        .append("SHA1: ").append(items[i].login.sha1).append("\n")
                        .append("SHA256: ").append(items[i].login.sha256).append("\n")
                        .append("Dob date: ").append(items[i].dob.date).append("\n")
                        .append("Dob age: ").append(items[i].dob.age).append("\n")
                        .append("Registered date: ").append(items[i].registered.date).append("\n")
                        .append("Registered age: ").append(items[i].registered.age).append("\n")
                        .append("Phone: ").append(items[i].phone).append("\n")
                        .append("Cell: ").append(items[i].cell).append("\n")
                        .append("ID name: ").append(items[i].id.name).append("\n")
                        .append("ID value: ").append(items[i].id.value).append("\n")
                        .append("Picture large: ").append(items[i].picture.large).append("\n")
                        .append("Picture medium: ").append(items[i].picture.medium).append("\n")
                        .append("Picture thumbnail: ").append(items[i].picture.thumbnail).append("\n")
                        .append("Nat: ") .append(items[i].nat)
                        binding.textView.text = stringBuilder

                    Glide.with(this@MainActivity)
                        .load(items[i].picture.medium)
                        .into(binding.imageView)
                }
            }
        }
    }
}