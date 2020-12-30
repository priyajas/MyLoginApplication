package com.example.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.entity.UserList
import com.example.myapplication.view.fragments.ListFragmentDirections
import kotlinx.android.synthetic.main.item_user.view.*

class UsersListAdapter(val usersList: ArrayList<UserList>) :
    RecyclerView.Adapter<UsersListAdapter.UserViewHolder>() {

    fun updateUserList(newUsersList: List<UserList>) {
        usersList.clear()
        usersList.addAll(newUsersList)
        notifyDataSetChanged()
    }

    class UserViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount() = usersList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.view.name.text = usersList[position].name
        holder.view.username.text = usersList[position].username
        holder.view.email.text = usersList[position].email
        holder.view.address.text =
            usersList[position].address.suite + "," + usersList[position].address.street + "," + usersList[position].address.city + "," + usersList[position].address.zipcode
        holder.view.phone.text = usersList[position].phone
        holder.view.website.text = usersList[position].website
        holder.view.company.text = usersList[position].company.name
        holder.view.setOnClickListener{
            val lat = usersList[position].address.geo.lat
            val lng = usersList[position].address.geo.lng
            val action=ListFragmentDirections.actionListFragmentToAddressFragment(lat,lng)
            Navigation.findNavController(it).navigate(action)

            }
        }



    }
