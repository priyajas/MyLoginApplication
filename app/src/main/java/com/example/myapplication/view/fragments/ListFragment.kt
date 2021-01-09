package com.example.myapplication.view.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.view.adapter.UsersListAdapter
import com.example.myapplication.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {
    private lateinit var listviewmodel: ListViewModel
    private val userslistadapter = UsersListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listviewmodel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        listviewmodel.refresh()
        usersList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userslistadapter
        }
        refreshLayout.setOnRefreshListener {
            listError.visibility = View.GONE
            usersList.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            listviewmodel.refresh()
            refreshLayout.isRefreshing = false

        }
        observeViewModel()
    }

    fun observeViewModel() {
        listviewmodel.users.observe(viewLifecycleOwner, Observer { users ->
            users?.let {
                usersList.visibility = View.VISIBLE
                userslistadapter.updateUserList(users)
            }
        })
        listviewmodel.usersLoadError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let {
                listError.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    loadingView.visibility = View.GONE
                    usersList.visibility = View.GONE
                }
            }

        })
        listviewmodel.loading.observe(viewLifecycleOwner, Observer { isloading ->
            isloading?.let {
                loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    listError.visibility = View.GONE
                    usersList.visibility = View.GONE
                }
            }

        })


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater!!.inflate(R.menu.menu_settings, menu)
        menu!!.findItem(R.id.logout).isVisible=true
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item!!.getItemId()
        if (id == R.id.logout) {
            findNavController().navigate(R.id.action_listFragment_to_loginFragment)
        }
            return super.onOptionsItemSelected(item)
    }


}