package com.example.roompractice.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roompractice.activity.home.MainActivityViewModel
import com.example.roompractice.adapter.OnUserListOptionClickListener
import com.example.roompractice.adapter.UserListAdapter
import com.example.roompractice.databinding.ActivityMainBinding
import com.example.roompractice.roomDatabase.MyRoomDatabase
import com.example.roompractice.roomDatabase.tables.UserTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var screen: ActivityMainBinding
    private lateinit var adapter: UserListAdapter
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screen = ActivityMainBinding.inflate(layoutInflater)
        setContentView(screen.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
        )
            .get(MainActivityViewModel::class.java)
        screen.rv.layoutManager = LinearLayoutManager(this)
        adapter = UserListAdapter(object : DiffUtil.ItemCallback<UserTable>() {
            override fun areItemsTheSame(oldItem: UserTable, newItem: UserTable): Boolean =
                oldItem._id == newItem._id

            override fun areContentsTheSame(oldItem: UserTable, newItem: UserTable): Boolean =
                oldItem.toString() == newItem.toString()
        }, object : OnUserListOptionClickListener {
            override fun showUserDetails(model: UserTable) {
                CoroutineScope(Main).launch {
                    val newModel =
                        MyRoomDatabase.getInstance(this@MainActivity).roomDao().getUser(model._id)
                    Toast.makeText(this@MainActivity, newModel?.userName, Toast.LENGTH_SHORT).show()
                }
            }
        })
        screen.rv.adapter = adapter

        viewModel.getUsers().observe(this@MainActivity, Observer {
            adapter.submitList(it)
        })

        screen.fab.setOnClickListener {
            viewModel.insert(UserTable(userName = "Kirtan"))
        }
    }

}