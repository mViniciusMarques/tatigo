package solutions.empire42.tatianego.fragment

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_cadastro_produto.*


import solutions.empire42.tatianego.R
import java.io.File

import android.os.Environment

import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener


class CadastroProdutoFragment : Fragment() {

    val PICK_PHOTO_FOR_AVATAR = 1
    val TAKE_PHOTO_REQUEST = 2


    var languages = arrayOf("oi", "tchau")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v: View  =  inflater.inflate(R.layout.fragment_cadastro_produto, container, false);

        var  values = arrayOf("Bolos", " Saladas de Frutas","Bombom");


        val spinner = v.findViewById(R.id.spinner_cadastro) as Spinner
        val adapter = ArrayAdapter(this.activity, android.R.layout.simple_spinner_item, values)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter

        return v;
    }

    override fun onResume() {
        super.onResume()
        lastday.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR)
        }


        camera.setOnClickListener {
            launchCamera()
        }



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error
                return
            }
            data.data
            val inputStream = context!!.contentResolver.openInputStream(data.data)
        }

        if (resultCode == Activity.RESULT_OK
            && requestCode == TAKE_PHOTO_REQUEST) {
           // processCapturedPhoto()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    private fun validatePermissions() {
        Dexter.withActivity(Activity())
            .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object: PermissionListener {
                override fun onPermissionGranted(
                    response: PermissionGrantedResponse?) {
                    launchCamera()
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?) {
                    AlertDialog.Builder(context)
                        .setTitle(
                           "sdsad")
                        .setMessage(
                           "bomobom")
                        .setNegativeButton(
                            android.R.string.cancel,
                            { dialog, _ ->
                                dialog.dismiss()
                                token?.cancelPermissionRequest()
                            })
                        .setPositiveButton(android.R.string.ok,
                            { dialog, _ ->
                                dialog.dismiss()
                                token?.continuePermissionRequest()
                            })
                        .setOnDismissListener({
                            token?.cancelPermissionRequest() })
                        .show()
                }

                override fun onPermissionDenied(
                    response: PermissionDeniedResponse?) {

                }
            })
            .check()
    }

    private fun launchCamera() {
        val values = ContentValues(1)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
//        val fileUri = contentResolver
//            .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                values)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(context?.packageManager) != null) {
           // mCurrentPhotoPath = fileUri.toString()
          //  intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            startActivityForResult(intent, TAKE_PHOTO_REQUEST)
        }
    }




}