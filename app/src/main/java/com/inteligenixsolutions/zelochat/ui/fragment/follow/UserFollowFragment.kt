package com.inteligenixsolutions.zelochat.ui.fragment.follow


import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import com.inteligenixsolutions.zelochat.R
import com.inteligenixsolutions.zelochat.base.BaseFragment
import com.inteligenixsolutions.zelochat.data.image.Image
import com.inteligenixsolutions.zelochat.data.post.Post
import com.inteligenixsolutions.zelochat.databinding.FragmentUserFollowBinding
import com.inteligenixsolutions.zelochat.ui.fragment.home.HomeFragment
import com.inteligenixsolutions.zelochat.ui.fragment.profile.ProfileViewModel
import com.inteligenixsolutions.zelochat.ui.fragment.social.ImageAdapter
import com.inteligenixsolutions.zelochat.ui.fragment.social.ImageListingAdapter
import com.inteligenixsolutions.zelochat.ui.fragment.social.SocialViewModel
import com.inteligenixsolutions.zelochat.utils.ActiveUserOffline.ImageUriList.imageUriList
import com.inteligenixsolutions.zelochat.utils.FirebaseStorageConstants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserFollowFragment : BaseFragment<FragmentUserFollowBinding>(FragmentUserFollowBinding::inflate) {

 //   private val viewModel: SocialViewModel by viewModels()

   //private val viewModel: ImageViewModel by viewModels()
    @Inject
    lateinit var user:FirebaseUser
    @Inject
    lateinit var databaseReference: DatabaseReference
    val images= mutableListOf<String>()
    private val viewModel: PostViewModel by viewModels()
    //    val adapter by lazy {
//        ImageListingAdapter(
//            onCancelClicked = { pos, item -> onRemoveImage(pos,item)}
//        )
//    }
    var objNote: Image? = null

    var imageUris: MutableList<Uri> = arrayListOf()
    var imageString: MutableList<String> = arrayListOf()



    var PICK_IMAGES_REQUEST_CODE = 123

    lateinit var adapter: ImageAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ImageAdapter(imageUris)
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.postShowImageView.layoutManager = layoutManager
        binding.postShowImageView.adapter = adapter

        Toast.makeText(requireContext(), "", Toast.LENGTH_SHORT).show()

        binding.onBackBtn.setOnClickListener {
            navigateTo(HomeFragment())
        }

        viewModel.responseUpdatePost.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), "upload image success fireStore", Toast.LENGTH_SHORT).show()
        }

viewModel.responsePost.observe(viewLifecycleOwner){
    if (it.isNullOrEmpty()){
        Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()

    }else{
        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
    }

}
        images.add(imageUris.toString())

 binding.postBtn.setOnClickListener {

     val houseName= binding.houseNameEd.text.toString().trim()
     val houseNumber= binding.houseNoEd.text.toString().trim()
     val houseAddress= binding.houseAddressEd.text.toString().trim()
     val phone= binding.phoneNumber.text.toString().trim()
    handelPost(houseName,houseNumber,houseAddress,phone,imageString)



 }
//        binding.postImageUploadBtn.setOnClickListener {
//            ImagePicker.with(this)
//                //.crop()
//                .compress(1024)
//                .galleryOnly()
//                .createIntent { intent ->
//                    startForProfileImageResult.launch(intent)
//                }
//        }

        binding.postImageUploadBtn.setOnClickListener {
            openImagePicker()
        }

    }


    private fun navigateTo(fragment: Fragment) {
        val transaction = (activity as FragmentActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)

        transaction.commit()
    }

//    private val startForProfileImageResult =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
//            val resultCode = result.resultCode
//            val data = result.data
//            if (resultCode == Activity.RESULT_OK) {
//                val fileUri = data?.data!!
//                imageUris.add(fileUri)
//                Log.d("TAG", imageUris.toString())
//
//
//
//
//            } else if (resultCode == ImagePicker.RESULT_ERROR) {
//                // binding.progressBar.hide()
//                //  toast(ImagePicker.getError(data))
//                Toast.makeText(requireContext(), "upload error", Toast.LENGTH_SHORT).show()
//            } else {
//                //binding.progressBar.hide()
//                Log.e("TAG", "Task Cancelled")
//            }
//        }


    private fun onRemoveImage(pos: Int, item: Uri) {
//        adapter.removeItem(pos)
//        if (objNote != null){
//            // binding.edit.performClick()
//        }
    }


    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)

        startActivityForResult(intent, PICK_IMAGES_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGES_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val clipData: ClipData? = data.clipData

                if (clipData != null) {
                    // Multiple images selected
                    for (i in 0 until clipData.itemCount) {
                        val uri: Uri = clipData.getItemAt(i).uri
                        imageUris.add(uri)
                        imageString.add(uri.toString())

                    }
                    viewModel.uploadPost(imageUris)
                } else {
                    // Single image selected
                    val uri: Uri = data.data!!
                    imageUris.add(uri)
                    Log.d("TAG",imageUris.toString())
                }

                // Update the RecyclerView or perform any other actions with selected images
                adapter.notifyDataSetChanged()
            }
        }


    }


    private fun handelPost (hName:String,houseNumber:String,houseAddress:String,phone:String,imageUri:List<String>){
        val request = Post(postByI = "", houseName = hName,houseNumber= houseNumber,houseAddress= houseAddress, phoneNumber = phone, postUsi = imageUri)
        viewModel.post(request)
    }


}