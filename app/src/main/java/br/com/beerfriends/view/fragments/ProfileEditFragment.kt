package br.com.beerfriends.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.beerfriends.R
import br.com.beerfriends.databinding.FragmentProfileEditBinding
import br.com.beerfriends.model.User
import br.com.beerfriends.model.UserRepository
import br.com.beerfriends.viewmodel.ProfileEditViewModel
import com.google.android.material.snackbar.Snackbar

class ProfileEditFragment : Fragment() {

    private lateinit var profileEditViewModel: ProfileEditViewModel
    private var _binding: FragmentProfileEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPreferences = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val uid = sharedPreferences.getString("uid", "Usuário não logado")

        profileEditViewModel = ViewModelProvider(this, ProfileEditViewModel.ProfileEditViewModelFactory(
            UserRepository(), uid)).get(ProfileEditViewModel::class.java)

        _binding = FragmentProfileEditBinding.inflate(inflater, container, false)
        val root: View = binding.root

        profileEditViewModel.user.observe(viewLifecycleOwner, Observer { user ->
            binding.user = user
        })

        root.findViewById<Button>(R.id.button_save).setOnClickListener{
            val user =  User(
                uid = uid!!,
                name = binding.username.text.toString(),
                email = binding.email.text.toString()
            )

            profileEditViewModel.saveUser(user)

            val sharedPreferences = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putString("uid", user.uid)
                putString("name", user.name)
                putString("email", user.email)
                commit()
            }

            Snackbar.make(root, "Profile alterado com sucesso", Snackbar.LENGTH_LONG)
                .setAction("Sucesso", null).show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}