package br.com.beerfriends.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.beerfriends.databinding.FragmentFriendListBinding
import br.com.beerfriends.model.FriendRepository
import br.com.beerfriends.view.adapter.FriendAdapter
import br.com.beerfriends.viewmodel.FriendListViewModel

class FriendListFragment : Fragment() {

    private lateinit var friendListViewModel: FriendListViewModel
    private var _binding: FragmentFriendListBinding? = null
    private val friendAdapter = FriendAdapter(arrayListOf())
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        friendListViewModel = ViewModelProvider(this, FriendListViewModel.FriendListViewModelFactory(
            FriendRepository())).get(FriendListViewModel::class.java)

        _binding = FragmentFriendListBinding.inflate(inflater, container, false)

        val root: View = binding.root

        binding.friends.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = friendAdapter
        }

        friendListViewModel.refreshFriends()

        friendListViewModel.items.observe(viewLifecycleOwner) { friends ->
            friends?.let {
                friendAdapter.update(it)
            }
        }

        friendListViewModel.requestError.observe(viewLifecycleOwner) { wrapper ->
            wrapper.getContentIfNotHandled()?.let { errorMessage ->
                Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}