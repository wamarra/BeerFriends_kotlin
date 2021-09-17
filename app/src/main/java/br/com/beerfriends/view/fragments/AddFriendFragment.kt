package br.com.beerfriends.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.beerfriends.R
import br.com.beerfriends.databinding.FragmentAddFriendBinding
import br.com.beerfriends.model.Friend
import br.com.beerfriends.model.FriendRepository
import br.com.beerfriends.viewmodel.AddFriendViewModel
import com.google.android.material.snackbar.Snackbar

class AddFriendFragment : Fragment() {

    private lateinit var addFriendViewModel: AddFriendViewModel
    private var _binding: FragmentAddFriendBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        addFriendViewModel = ViewModelProvider(this, AddFriendViewModel.AddFriendViewModelFactory(
            FriendRepository())).get(AddFriendViewModel::class.java)

        _binding = FragmentAddFriendBinding.inflate(inflater, container, false)

        val root: View = binding.root

        root.findViewById<Button>(R.id.button_save).setOnClickListener{
            addFriendViewModel.insertFriend(Friend(
                name = binding.username.text.toString(),
                email = binding.email.text.toString(),
                phone = binding.phone.text.toString(),
                website = binding.website.text.toString()
            ))

            Snackbar.make(root, "Amigo cadastrado com sucesso", Snackbar.LENGTH_LONG)
                .setAction("Sucesso", null).show()

            binding.username.text = null
            binding.email.text = null
            binding.phone.text = null
            binding.website.text = null
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}