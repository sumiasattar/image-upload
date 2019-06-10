package friendsforever.fyp.app.mapactivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ImageuploadActivity extends AppCompatActivity {

    private String mStrCatTitle;
    private StorageReference mProfilePicStorageReference;
    private static final int RC_PHOTO_PICKER = 1;
    private Uri selectedProfileImageUri;
    private ImageView profileImageView;
    Button mSelectImgBtn, muploadBtn;
    String downloadUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageupload);
        mProfilePicStorageReference = FirebaseStorage.getInstance().getReference().child("pictures");


        profileImageView=findViewById( R.id.imgvw);
        mSelectImgBtn=findViewById(R.id.getImg);
        mSelectImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getProfilePicture();
            }
        });
        mSelectImgBtn=findViewById(R.id.uploadImg);
        mSelectImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProfilePicStorageReference = mProfilePicStorageReference.child(selectedProfileImageUri.getLastPathSegment());
                mProfilePicStorageReference.putFile(selectedProfileImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Toast.makeText(ImageuploadActivity.this, "image uploaded", Toast.LENGTH_SHORT).show();

                        mProfilePicStorageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                downloadUri=uri.toString();
                                uploadProduct(downloadUri);
                            }
                        });
                    }
                });

            }
        });
    }

    public void getProfilePicture() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri selectedImageUri = data.getData();
            selectedProfileImageUri = selectedImageUri;
            profileImageView.setImageURI(selectedImageUri);
            profileImageView.setVisibility(View.VISIBLE);
        }

    }

}
