package com.example.instalker.fragments

import android.content.Intent
import android.os.Bundle
import android.view.Display.Mode
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instalker.Models.User
import com.example.instalker.R
import com.example.instalker.SignUpActivity
import com.example.instalker.databinding.FragmentProfileBinding
import com.example.instalker.utils.User_Node
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class ProfileFragment : Fragment() {
 private lateinit var binding:FragmentProfileBinding
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      binding=FragmentProfileBinding.inflate(inflater, container, false)
        binding.editProfile.setOnClickListener {
            val intent=Intent(activity,SignUpActivity::class.java)
            intent.putExtra("MODE",1)
            activity?.startActivity(intent)
            activity?.finish()

        }
        return binding.root
    }
    companion object{

        }

    override fun onStart() {
        super.onStart()
        Firebase.firestore.collection(User_Node).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {

            val user:User=it.toObject<User>()!!
            binding.name.text=user.name
            binding.bio.text=user.email
            if (!user.image.isNullOrEmpty()){
                Picasso.get().load(user.image).into(binding.profileImage)
            }
        }

    }
}